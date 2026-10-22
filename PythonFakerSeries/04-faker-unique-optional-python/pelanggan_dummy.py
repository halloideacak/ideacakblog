# Versi gabungan: 1.000 pelanggan dummy yang lolos import ke tabel dengan email UNIQUE
import csv
import sqlite3
from collections import Counter, OrderedDict

from faker import Faker

Faker.seed(2026)
fake = Faker(OrderedDict([("id_ID", 9), ("en_US", 1)]))

METODE_BAYAR = OrderedDict([
    ("Tunai", 6),
    ("QRIS", 3),
    ("Transfer", 1),
])


def buat_pelanggan():
    return {
        "id": fake.uuid4(),
        "nama": fake.name(),
        "email": fake.unique.free_email(),
        "telepon": fake.optional.phone_number(prob=0.7),
        "kota": fake["id_ID"].city(),
        "metode_bayar": fake.random_element(METODE_BAYAR),
        "tanggal_daftar": fake.date_this_decade().isoformat(),
    }


def simpan_csv(daftar_pelanggan, nama_file):
    with open(nama_file, "w", newline="", encoding="utf-8") as file:
        writer = csv.DictWriter(file, fieldnames=daftar_pelanggan[0].keys())
        writer.writeheader()
        writer.writerows(daftar_pelanggan)


def simpan_sqlite(daftar_pelanggan):
    koneksi = sqlite3.connect(":memory:")
    koneksi.execute("""
        CREATE TABLE pelanggan (
            id TEXT PRIMARY KEY,
            nama TEXT NOT NULL,
            email TEXT NOT NULL UNIQUE,
            telepon TEXT,
            kota TEXT,
            metode_bayar TEXT,
            tanggal_daftar TEXT
        )
    """)
    koneksi.executemany(
        "INSERT INTO pelanggan VALUES (:id, :nama, :email, :telepon, :kota, :metode_bayar, :tanggal_daftar)",
        daftar_pelanggan,
    )
    return koneksi.execute("SELECT COUNT(*) FROM pelanggan").fetchone()[0]


daftar_pelanggan = [buat_pelanggan() for _ in range(1000)]
simpan_csv(daftar_pelanggan, "pelanggan.csv")

print("Tersimpan di SQLite:", simpan_sqlite(daftar_pelanggan), "baris")
print("Telepon kosong:", sum(1 for p in daftar_pelanggan if p["telepon"] is None))
print("Metode bayar:", Counter(p["metode_bayar"] for p in daftar_pelanggan))
