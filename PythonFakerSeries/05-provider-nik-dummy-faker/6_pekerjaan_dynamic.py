# DynamicProvider: provider dari sebuah daftar, tanpa menulis class
from faker import Faker
from faker.providers import DynamicProvider

Faker.seed(2026)
fake = Faker("id_ID")
print("fake.job():", fake.job())

pekerjaan_provider = DynamicProvider(
    provider_name="pekerjaan",
    elements=["Pelajar/Mahasiswa", "Karyawan Swasta", "Wiraswasta", "Pegawai Negeri Sipil",
              "Petani", "Guru", "Pedagang", "Mengurus Rumah Tangga"],
)
fake.add_provider(pekerjaan_provider)
print([fake.pekerjaan() for _ in range(4)])
