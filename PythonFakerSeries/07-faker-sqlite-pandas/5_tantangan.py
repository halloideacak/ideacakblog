# Jawaban tantangan artikel 7 (jalankan 3_isi_toko.py dulu)
import sqlite3
from collections import Counter
from datetime import date

import pandas as pd
from faker import Faker

koneksi = sqlite3.connect("toko.db")
df = pd.read_sql_query("SELECT * FROM transaksi", koneksi, parse_dates=["waktu"])
koneksi.close()

# Tantangan 1: hari dengan omzet terbesar
omzet_harian = df.groupby(df["waktu"].dt.date)["nominal"].sum()
print("Hari teramai:", omzet_harian.idxmax(), "omzet", omzet_harian.max())

# Tantangan 2: rata-rata nominal per metode bayar
print(df.groupby("metode_bayar")["nominal"].mean().round(0).sort_values(ascending=False))

# Tantangan 3: toko tutup setiap hari Minggu
Faker.seed(2026)
fake = Faker("id_ID")


def tanggal_buka(fake, mulai, selesai):
    while True:
        tanggal = fake.date_between(start_date=mulai, end_date=selesai)
        if tanggal.weekday() != 6:          # 6 = Minggu
            return tanggal


daftar_tanggal = [tanggal_buka(fake, date(2026, 9, 1), date(2026, 9, 30)) for _ in range(2000)]
print(sorted(Counter(t.strftime("%A") for t in daftar_tanggal).items()))
