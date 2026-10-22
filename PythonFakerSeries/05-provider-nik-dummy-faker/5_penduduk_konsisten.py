# Data penduduk yang saling cocok: nama, jenis kelamin, tanggal lahir, kecamatan, dan NIK
import json

from faker import Faker

from nik_provider import NikProvider
from validasi_nik import bedah_nik, muat_kecamatan

Faker.seed(2026)
fake = Faker("id_ID")
fake.add_provider(NikProvider)
kecamatan = muat_kecamatan()
KODE = tuple(kecamatan)


def buat_penduduk():
    jenis_kelamin = fake.random_element(("laki-laki", "perempuan"))
    if jenis_kelamin == "laki-laki":
        nama = f"{fake.first_name_male()} {fake.last_name_male()}"
    else:
        nama = f"{fake.first_name_female()} {fake.last_name_female()}"
    tanggal_lahir = fake.date_of_birth(minimum_age=17, maximum_age=80)
    kode = fake.random_element(KODE)
    return {
        "nik": fake.unique.nik(jenis_kelamin=jenis_kelamin, tanggal_lahir=tanggal_lahir, kode_kecamatan=kode),
        "nama": nama,
        "jenis_kelamin": jenis_kelamin,
        "tanggal_lahir": tanggal_lahir.isoformat(),
        "kecamatan": kecamatan[kode]["kecamatan"],
        "kabupaten_kota": kecamatan[kode]["kabupaten_kota"],
    }


daftar_penduduk = [buat_penduduk() for _ in range(1000)]
print(json.dumps(daftar_penduduk[:2], ensure_ascii=False, indent=2))

# cek silang: isi NIK harus sama dengan kolom lainnya
cocok = 0
for p in daftar_penduduk:
    isi = bedah_nik(p["nik"], kecamatan)
    if (isi["jenis_kelamin"] == p["jenis_kelamin"]
            and isi["tanggal_lahir"].isoformat() == p["tanggal_lahir"]
            and isi["kecamatan"] == p["kecamatan"]):
        cocok += 1
print("NIK cocok dengan data lainnya:", cocok, "dari 1.000")
