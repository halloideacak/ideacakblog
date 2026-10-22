# Jawaban tantangan artikel 5
from collections import Counter

from faker import Faker
from faker.providers import DynamicProvider

from nik_provider import KODE_KECAMATAN, NikProvider
from validasi_nik import bedah_nik, muat_kecamatan

Faker.seed(2026)
fake = Faker("id_ID")
fake.add_provider(NikProvider)
kecamatan = muat_kecamatan()


# Tantangan 1: NIK khusus Kabupaten Bogor (kode berawalan 3201)
KODE_BOGOR = tuple(kode for kode in KODE_KECAMATAN if kode.startswith("3201"))
daftar_nik = [fake.nik(kode_kecamatan=fake.random_element(KODE_BOGOR)) for _ in range(500)]
print("Kecamatan di Kabupaten Bogor:", len(KODE_BOGOR))
print(Counter(kecamatan[nik[:6]]["kabupaten_kota"] for nik in daftar_nik))


# Tantangan 2: 10 NIK perempuan berumur 20 sampai 30 tahun
for _ in range(10):
    nik = fake.nik(jenis_kelamin="perempuan", tanggal_lahir=fake.date_of_birth(minimum_age=20, maximum_age=30))
    isi = bedah_nik(nik, kecamatan)
    assert isi["jenis_kelamin"] == "perempuan"
print("Contoh:", nik, isi["tanggal_lahir"], isi["jenis_kelamin"])


# Tantangan 3: DynamicProvider golongan darah
fake.add_provider(DynamicProvider(provider_name="golongan_darah", elements=["A", "B", "AB", "O"]))
print([fake.golongan_darah() for _ in range(6)])
