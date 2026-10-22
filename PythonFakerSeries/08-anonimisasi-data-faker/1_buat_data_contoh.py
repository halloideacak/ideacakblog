# Membuat "data asli" contoh. Isinya tetap buatan Faker, jadi aman dipakai latihan.
import csv
from collections import OrderedDict

from faker import Faker

Faker.seed(99)
fake = Faker("id_ID")

pelanggan = [
    {
        "id": nomor,
        "nama": fake.name(),
        "email": fake.unique.free_email(),
        "telepon": fake.numerify("08##########"),
        "kota": fake.random_element(OrderedDict([("Jakarta", 4), ("Bandung", 2), ("Surabaya", 2), ("Bogor", 1), ("Depok", 1)])),
    }
    for nomor in range(1, 1001)
]

# transaksi dihubungkan ke pelanggan lewat email, seperti banyak file ekspor
transaksi = [
    {
        "id": nomor,
        "email_pelanggan": fake.random_element([p["email"] for p in pelanggan]),
        "nominal": fake.random_int(5_000, 300_000, step=500),
    }
    for nomor in range(1, 5001)
]

for nama_file, data in [("pelanggan.csv", pelanggan), ("transaksi.csv", transaksi)]:
    with open(nama_file, "w", newline="", encoding="utf-8") as file:
        writer = csv.DictWriter(file, fieldnames=data[0].keys())
        writer.writeheader()
        writer.writerows(data)

print(pelanggan[0])
print(transaksi[0])
