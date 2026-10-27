# Membaca toko.db dengan pandas dan membandingkan hasilnya dengan profil toko
import sqlite3

import pandas as pd

from toko import BOBOT_JAM, BOBOT_NOMINAL, METODE_BAYAR

koneksi = sqlite3.connect("toko.db")

# SQL saja sudah bisa menghitung transaksi per jam
print(koneksi.execute(
    """
    SELECT CAST(strftime('%H', waktu) AS INTEGER) AS jam, COUNT(*)
    FROM transaksi GROUP BY jam ORDER BY jam LIMIT 3
    """
).fetchall(), "\n")

df = pd.read_sql_query(
    """
    SELECT t.id, t.pelanggan_id, t.waktu, t.nominal, t.metode_bayar, p.nama, p.kota
    FROM transaksi t JOIN pelanggan p ON p.id = t.pelanggan_id
    """,
    koneksi,
    parse_dates=["waktu"],
)
koneksi.close()

jam = df["waktu"].dt.hour
print("Transaksi per jam:")
print(jam.value_counts().sort_index().to_string(), "\n")
print("Di luar jam buka:", (~jam.between(7, 21)).sum(), "\n")

# periode di profil toko, harapan dihitung dari bobot jam
BATAS = [6, 8, 10, 13, 16, 19, 21]
PERIODE = ["Pagi 07-08", "Sepi 09-10", "Makan siang 11-13", "Sepi 14-16", "Pulang kerja 17-19", "Malam 20-21"]
bobot = pd.Series(BOBOT_JAM)
harapan = bobot.groupby(pd.cut(bobot.index, BATAS, labels=PERIODE), observed=False).sum() / bobot.sum()
hasil = pd.cut(jam, BATAS, labels=PERIODE).value_counts(normalize=True)
print((pd.DataFrame({"harapan %": harapan, "hasil %": hasil}) * 100).round(1), "\n")

print("Nominal:")
print(df["nominal"].describe().round(0), "\n")

rentang = pd.cut(df["nominal"], [0, 30_000, 150_000, 1_000_000], labels=["kecil", "sedang", "besar"])
harapan = pd.Series(list(BOBOT_NOMINAL.values()), index=["kecil", "sedang", "besar"])
print((pd.DataFrame({
    "harapan %": harapan / harapan.sum(),
    "hasil %": rentang.value_counts(normalize=True),
}) * 100).round(1), "\n")

harapan = pd.Series(METODE_BAYAR)
print((pd.DataFrame({
    "harapan %": harapan / harapan.sum(),
    "hasil %": df["metode_bayar"].value_counts(normalize=True),
}) * 100).round(1), "\n")

per_pelanggan = df.groupby(["pelanggan_id", "nama"]).size().sort_values(ascending=False)
print("5 pelanggan dengan transaksi terbanyak:")
print(per_pelanggan.head(5), "\n")
print(f"Porsi transaksi dari 20% pelanggan teratas: {per_pelanggan.head(200).sum() / len(df):.0%}")
