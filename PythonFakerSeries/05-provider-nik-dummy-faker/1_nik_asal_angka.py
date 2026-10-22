# Cara naif: NIK dari 16 angka acak, lalu dicek dengan validator
from faker import Faker

from validasi_nik import muat_kecamatan, nik_valid

Faker.seed(2026)
fake = Faker("id_ID")
kecamatan = muat_kecamatan()

print("fake.ssn():", fake.ssn())

daftar_nik = [fake.numerify("################") for _ in range(1000)]
print("Contoh:", daftar_nik[:3])
print("Lolos validasi:", sum(nik_valid(nik, kecamatan) for nik in daftar_nik), "dari 1.000")
