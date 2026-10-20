"""Bedah NIK per kelompok digit sesuai Pasal 31 ayat (1) PP 40/2019,
lalu cek tanggal lahirnya pakai datetime."""
from datetime import date


def bedah_nik(nik: str, tahun_sekarang: int = date.today().year) -> dict:
    if len(nik) != 16 or not nik.isdigit():
        raise ValueError("NIK harus 16 digit angka")

    kode_wilayah = nik[:6]
    hari, bulan, tahun2 = int(nik[6:8]), int(nik[8:10]), int(nik[10:12])
    nomor_urut = nik[12:]

    # Khusus perempuan, tanggal lahir ditambah 40
    jenis_kelamin = "perempuan" if hari > 40 else "laki-laki"
    if hari > 40:
        hari -= 40

    # NIK cuma menyimpan 2 digit tahun. Asumsi: kalau lebih besar dari
    # 2 digit tahun sekarang, berarti lahir di abad 1900-an.
    abad = 2000 if tahun2 <= tahun_sekarang % 100 else 1900
    tanggal_lahir = date(abad + tahun2, bulan, hari)  # ValueError kalau tanggalnya mustahil

    return {
        "kode_wilayah": kode_wilayah,
        "tanggal_lahir": tanggal_lahir,
        "jenis_kelamin": jenis_kelamin,
        "nomor_urut": nomor_urut,
    }


if __name__ == "__main__":
    contoh = ["3201135505980000", "3201131505980000", "3201139905980000", "3201133002000000"]
    for nik in contoh:
        try:
            print(nik, "->", bedah_nik(nik))
        except ValueError as e:
            print(nik, "-> tidak valid:", e)
