# Pembuka artikel: 1.000 pelanggan dummy gagal masuk ke tabel yang email-nya UNIQUE
import sqlite3

from faker import Faker

Faker.seed(2026)
fake = Faker("id_ID")

koneksi = sqlite3.connect(":memory:")
koneksi.execute("CREATE TABLE pelanggan (id INTEGER PRIMARY KEY, nama TEXT, email TEXT UNIQUE)")

for nomor in range(1, 1001):
    nama = fake.name()
    email = fake.free_email()
    try:
        koneksi.execute("INSERT INTO pelanggan (nama, email) VALUES (?, ?)", (nama, email))
    except sqlite3.IntegrityError as e:
        print(f"Gagal di pelanggan ke-{nomor}: {nama} <{email}>")
        print("IntegrityError:", e)
        break
else:
    print("1.000 pelanggan berhasil disimpan")
