# Validasi form pendaftaran, versi setelah diperbaiki berkat test dengan data Faker
import re
from datetime import date

from validasi_nik import bedah_nik, muat_kecamatan

KECAMATAN = muat_kecamatan()


def hitung_umur(tanggal_lahir, hari_ini):
    belum_ulang_tahun = (hari_ini.month, hari_ini.day) < (tanggal_lahir.month, tanggal_lahir.day)
    return hari_ini.year - tanggal_lahir.year - belum_ulang_tahun


def normalisasi_telepon(telepon):
    # buang spasi, kurung, dan strip, lalu ubah +62 jadi 0
    angka = re.sub(r"[\s()-]", "", telepon)
    if angka.startswith("+62"):
        angka = "0" + angka[3:]
    return angka


def validasi_pendaftaran(data, hari_ini=None):
    hari_ini = hari_ini or date.today()
    kesalahan = []

    # gelar seperti "Ir." dan "S.Pd" memakai titik dan koma
    if not re.fullmatch(r"[A-Za-z][A-Za-z .,']*", data["nama"]):
        kesalahan.append("nama hanya boleh berisi huruf, spasi, titik, koma, dan apostrof")

    if not re.fullmatch(r"[\w.+-]+@[\w-]+(\.[\w-]+)+", data["email"]):
        kesalahan.append("format email tidak valid")

    telepon = data.get("telepon")
    if telepon is not None and not re.fullmatch(r"08\d{8,11}", normalisasi_telepon(telepon)):
        kesalahan.append("nomor telepon harus diawali 08 atau +628 dan berisi 10-13 angka")

    if hitung_umur(data["tanggal_lahir"], hari_ini) < 17:
        kesalahan.append("umur minimal 17 tahun")

    nik = data.get("nik")
    if nik is not None:
        try:
            isi = bedah_nik(nik, KECAMATAN, hari_ini)
        except ValueError as e:
            kesalahan.append(f"NIK tidak valid: {e}")
        else:
            if isi["tanggal_lahir"] != data["tanggal_lahir"]:
                kesalahan.append("tanggal lahir tidak sama dengan tanggal lahir di NIK")

    return kesalahan
