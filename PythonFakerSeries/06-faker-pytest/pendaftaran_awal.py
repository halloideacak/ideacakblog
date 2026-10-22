# Validasi form pendaftaran, versi awal (masih punya bug yang akan ditemukan test)
import re
from datetime import date


def hitung_umur(tanggal_lahir, hari_ini):
    belum_ulang_tahun = (hari_ini.month, hari_ini.day) < (tanggal_lahir.month, tanggal_lahir.day)
    return hari_ini.year - tanggal_lahir.year - belum_ulang_tahun


def validasi_pendaftaran(data, hari_ini=None):
    hari_ini = hari_ini or date.today()
    kesalahan = []

    if not re.fullmatch(r"[A-Za-z ]+", data["nama"]):
        kesalahan.append("nama hanya boleh berisi huruf dan spasi")

    if not re.fullmatch(r"[\w.+-]+@[\w-]+(\.[\w-]+)+", data["email"]):
        kesalahan.append("format email tidak valid")

    telepon = data.get("telepon")
    if telepon is not None and not re.fullmatch(r"08\d{8,11}", telepon):
        kesalahan.append("nomor telepon harus diawali 08 dan berisi 10-13 angka")

    if hitung_umur(data["tanggal_lahir"], hari_ini) < 17:
        kesalahan.append("umur minimal 17 tahun")

    return kesalahan
