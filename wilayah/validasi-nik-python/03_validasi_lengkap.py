"""Validasi NIK lengkap: format, tanggal lahir, dan kode kecamatan
dicocokkan ke data kode wilayah Kepmendagri 300.2.2-2138 Tahun 2025."""
import csv
from dataclasses import dataclass, field
from datetime import date
from pathlib import Path

FILE_KECAMATAN = Path(__file__).with_name("kode_kecamatan.csv")


def muat_kecamatan(path: Path = FILE_KECAMATAN) -> dict:
    with open(path, encoding="utf-8") as f:
        return {baris["kode"]: baris for baris in csv.DictReader(f)}


@dataclass
class HasilValidasi:
    nik: str
    valid: bool = True
    alasan: list = field(default_factory=list)
    wilayah: str = ""
    tanggal_lahir: date | None = None
    jenis_kelamin: str = ""

    def gagal(self, alasan: str):
        self.valid = False
        self.alasan.append(alasan)


def validasi_nik(nik: str, kecamatan: dict, hari_ini: date | None = None) -> HasilValidasi:
    hari_ini = hari_ini or date.today()
    hasil = HasilValidasi(nik)

    if len(nik) != 16 or not nik.isdigit():
        hasil.gagal("NIK harus 16 digit angka")
        return hasil

    # 1. Kode wilayah: 6 digit pertama
    kode = nik[:6]
    if kode.startswith("99"):
        # NIK WNI di luar negeri: 99 + kode negara + kode perwakilan (Pasal 32 ayat (3))
        hasil.wilayah = "Perwakilan RI di luar negeri"
    elif kode in kecamatan:
        k = kecamatan[kode]
        hasil.wilayah = f"{k['kecamatan']}, {k['kabupaten_kota']}, {k['provinsi']}"
    else:
        hasil.gagal(f"kode kecamatan {kode} tidak ada di data Kepmendagri 2025")

    # 2. Tanggal lahir: 6 digit kedua, perempuan ditambah 40
    hari, bulan, tahun2 = int(nik[6:8]), int(nik[8:10]), int(nik[10:12])
    jenis_kelamin = "perempuan" if hari > 40 else "laki-laki"
    if hari > 40:
        hari -= 40
    abad = 2000 if tahun2 <= hari_ini.year % 100 else 1900
    try:
        hasil.tanggal_lahir = date(abad + tahun2, bulan, hari)
    except ValueError:
        hasil.gagal(f"tanggal lahir {nik[6:12]} tidak mungkin ada")
    else:
        hasil.jenis_kelamin = jenis_kelamin
        if hasil.tanggal_lahir > hari_ini:
            hasil.gagal("tanggal lahir ada di masa depan")

    return hasil


if __name__ == "__main__":
    kecamatan = muat_kecamatan()
    contoh = [
        "3201135505980000",  # dummy: Bojonggede, perempuan, 15 Mei 1998
        "3201131505980000",  # dummy: sama, tapi laki-laki
        "3201139905980000",  # tanggal 99
        "3201133002000000",  # 30 Februari
        "3299015505980000",  # kode kecamatan tidak ada
        "9911025505980000",  # pola NIK luar negeri
    ]
    for nik in contoh:
        h = validasi_nik(nik, kecamatan)
        status = "VALID" if h.valid else "TIDAK VALID"
        print(f"{nik}  {status:<11}  {h.wilayah or '-'} | {h.jenis_kelamin} {h.tanggal_lahir or ''}")
        for a in h.alasan:
            print(" " * 18, "-", a)
