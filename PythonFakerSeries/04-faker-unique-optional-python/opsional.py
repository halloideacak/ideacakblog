# fake.optional: kolom yang kadang kosong, seperti data asli
import csv
import json

from faker import Faker

Faker.seed(2026)
fake = Faker("id_ID")

daftar_telepon = [fake.optional.phone_number(prob=0.7) for _ in range(1000)]
print("Telepon kosong:", daftar_telepon.count(None), "dari 1.000")

pelanggan = [
    {"nama": fake.name(), "telepon": fake.optional.phone_number(prob=0.7)}
    for _ in range(4)
]

with open("pelanggan_opsional.csv", "w", newline="", encoding="utf-8") as file:
    writer = csv.DictWriter(file, fieldnames=["nama", "telepon"])
    writer.writeheader()
    writer.writerows(pelanggan)

print(open("pelanggan_opsional.csv", encoding="utf-8").read())
print(json.dumps(pelanggan, ensure_ascii=False, indent=2))
