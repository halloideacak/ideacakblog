# 1.000 NIK dari provider: lolos validasi, dan isinya bisa dibaca balik
from collections import Counter

from faker import Faker

from nik_provider import NikProvider
from validasi_nik import bedah_nik, muat_kecamatan, nik_valid

Faker.seed(2026)
fake = Faker("id_ID")
fake.add_provider(NikProvider)
kecamatan = muat_kecamatan()

daftar_nik = [fake.unique.nik() for _ in range(1000)]
print("Lolos validasi:", sum(nik_valid(nik, kecamatan) for nik in daftar_nik), "dari 1.000")
print("NIK berbeda:", len(set(daftar_nik)))
print(Counter(bedah_nik(nik, kecamatan)["jenis_kelamin"] for nik in daftar_nik))

# umur maksimal 100: NIK hanya menyimpan dua digit tahun
gagal = Counter()
for _ in range(1000):
    nik = fake.nik(tanggal_lahir=fake.date_of_birth(minimum_age=17, maximum_age=100))
    try:
        bedah_nik(nik, kecamatan)
    except ValueError as e:
        gagal[str(e)] += 1
print("maximum_age=100:", gagal)
