# Skema database dan pembuat data dummy toko yang dipakai skrip 2 sampai 5
import sqlite3
from collections import OrderedDict
from datetime import date, datetime, time

from faker import Faker

SKEMA = """
CREATE TABLE IF NOT EXISTS pelanggan (
    id INTEGER PRIMARY KEY,
    nama TEXT NOT NULL,
    email TEXT NOT NULL UNIQUE,
    kota TEXT NOT NULL
);
CREATE TABLE IF NOT EXISTS transaksi (
    id INTEGER PRIMARY KEY,
    pelanggan_id INTEGER NOT NULL REFERENCES pelanggan(id),
    waktu TEXT NOT NULL,
    nominal INTEGER NOT NULL,
    metode_bayar TEXT NOT NULL
);
"""

# toko buka jam 7 sampai 21, ramai di jam makan siang dan pulang kerja
BOBOT_JAM = OrderedDict([
    (7, 4), (8, 5), (9, 3), (10, 3), (11, 6), (12, 10), (13, 7),
    (14, 3), (15, 3), (16, 4), (17, 7), (18, 10), (19, 9), (20, 5), (21, 2),
])

# kebanyakan belanja kecil, sesekali belanja besar
BOBOT_NOMINAL = OrderedDict([
    ((5_000, 30_000), 60),
    ((30_000, 150_000), 30),
    ((150_000, 1_000_000), 10),
])

METODE_BAYAR = OrderedDict([("Tunai", 6), ("QRIS", 3), ("Transfer", 1)])


def buka_database(path):
    koneksi = sqlite3.connect(path)
    koneksi.execute("PRAGMA foreign_keys = ON")
    koneksi.executescript(SKEMA)
    return koneksi


def buat_pelanggan(fake, jumlah):
    return [
        (nomor, fake.name(), fake.unique.free_email(), fake.city())
        for nomor in range(1, jumlah + 1)
    ]


def buat_transaksi(fake, daftar_id_pelanggan, jumlah, mulai=date(2026, 9, 1), selesai=date(2026, 9, 30)):
    # 20% pelanggan adalah langganan yang belanjanya 8 kali lebih sering
    bobot_pelanggan = OrderedDict(
        (id_pelanggan, 8 if fake.boolean(chance_of_getting_true=20) else 1)
        for id_pelanggan in daftar_id_pelanggan
    )
    hasil = []
    for nomor in range(1, jumlah + 1):
        tanggal = fake.date_between(start_date=mulai, end_date=selesai)
        jam = fake.random_element(BOBOT_JAM)
        waktu = datetime.combine(tanggal, time(jam, fake.random_int(0, 59)))
        batas_bawah, batas_atas = fake.random_element(BOBOT_NOMINAL)
        nominal = fake.random_int(batas_bawah, batas_atas, step=500)
        hasil.append((
            nomor,
            fake.random_element(bobot_pelanggan),
            waktu.isoformat(sep=" "),
            nominal,
            fake.random_element(METODE_BAYAR),
        ))
    return hasil


def isi_database(path, jumlah_pelanggan=1000, jumlah_transaksi=20000, seed=2026):
    Faker.seed(seed)
    fake = Faker("id_ID")
    koneksi = buka_database(path)
    pelanggan = buat_pelanggan(fake, jumlah_pelanggan)
    transaksi = buat_transaksi(fake, [p[0] for p in pelanggan], jumlah_transaksi)
    with koneksi:
        koneksi.executemany("INSERT INTO pelanggan VALUES (?, ?, ?, ?)", pelanggan)
        koneksi.executemany("INSERT INTO transaksi VALUES (?, ?, ?, ?, ?)", transaksi)
    return koneksi
