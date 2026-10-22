# Validator NIK ringkas, diambil dari artikel Validasi NIK Python di ideacak.com.
# Hanya mengecek format (kode kecamatan dan tanggal lahir), bukan bukti NIK terdaftar.
import csv
from datetime import date
from pathlib import Path

FILE_KECAMATAN = Path(__file__).with_name("kode_kecamatan.csv")


def muat_kecamatan(path=FILE_KECAMATAN):
    with open(path, encoding="utf-8") as f:
        return {baris["kode"]: baris for baris in csv.DictReader(f)}


def bedah_nik(nik, kecamatan, hari_ini=None):
    """Kembalikan dict isi NIK, atau lempar ValueError kalau formatnya mustahil."""
    hari_ini = hari_ini or date.today()
    if len(nik) != 16 or not nik.isdigit():
        raise ValueError("NIK harus 16 digit angka")

    kode = nik[:6]
    if kode not in kecamatan:
        raise ValueError(f"kode kecamatan {kode} tidak ada di data Kepmendagri 2025")

    hari, bulan, tahun2 = int(nik[6:8]), int(nik[8:10]), int(nik[10:12])
    jenis_kelamin = "perempuan" if hari > 40 else "laki-laki"
    if hari > 40:
        hari -= 40
    abad = 2000 if tahun2 <= hari_ini.year % 100 else 1900
    tanggal_lahir = date(abad + tahun2, bulan, hari)   # ValueError kalau tanggal mustahil
    if tanggal_lahir > hari_ini:
        raise ValueError("tanggal lahir ada di masa depan")

    return {
        "kecamatan": kecamatan[kode]["kecamatan"],
        "tanggal_lahir": tanggal_lahir,
        "jenis_kelamin": jenis_kelamin,
        "nomor_urut": nik[12:],
    }


def nik_valid(nik, kecamatan):
    try:
        bedah_nik(nik, kecamatan)
        return True
    except ValueError:
        return False
