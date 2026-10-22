# Mendaftarkan NikProvider lalu memakainya seperti method Faker biasa
from datetime import date

from faker import Faker

from nik_provider import NikProvider
from validasi_nik import bedah_nik, muat_kecamatan

Faker.seed(2026)
fake = Faker("id_ID")
fake.add_provider(NikProvider)
kecamatan = muat_kecamatan()

print([fake.nik() for _ in range(3)])

nik = fake.nik(jenis_kelamin="perempuan", tanggal_lahir=date(1998, 5, 15), kode_kecamatan="320113")
print(nik, bedah_nik(nik, kecamatan))
