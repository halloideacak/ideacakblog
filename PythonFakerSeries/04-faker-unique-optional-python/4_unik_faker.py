# fake.unique: Faker yang mengingat hasilnya sendiri
from faker import Faker
from faker.exceptions import UniquenessException

Faker.seed(2026)
fake = Faker("id_ID")

daftar_email = [fake.unique.free_email() for _ in range(1000)]
print(len(daftar_email), "email,", len(set(daftar_email)), "berbeda")

# stok nilai yang mungkin cuma 20, tapi diminta 21
try:
    nomor_meja = [fake.unique.random_int(min=1, max=20) for _ in range(21)]
except UniquenessException as e:
    print("UniquenessException:", e)

# Faker.seed() tidak menghapus ingatan unique
Faker.seed(2026)
try:
    fake.unique.random_int(min=1, max=20)
except UniquenessException:
    print("Setelah seed ulang, nomor meja 1-20 masih dianggap terpakai")

fake.unique.clear()
print("Setelah clear():", fake.unique.random_int(min=1, max=20))
