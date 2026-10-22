# Membaca toko.db dengan pandas dan melihat apakah polanya masuk akal
import sqlite3

import pandas as pd

koneksi = sqlite3.connect("toko.db")
df = pd.read_sql_query(
    """
    SELECT t.id, t.pelanggan_id, t.waktu, t.nominal, t.metode_bayar, p.nama, p.kota
    FROM transaksi t JOIN pelanggan p ON p.id = t.pelanggan_id
    """,
    koneksi,
    parse_dates=["waktu"],
)
koneksi.close()

print(df.dtypes, "\n")

print("Transaksi per jam:")
print(df["waktu"].dt.hour.value_counts().sort_index().to_string(), "\n")

print("Omzet per metode bayar:")
print(df.groupby("metode_bayar")["nominal"].agg(["count", "sum"]).sort_values("sum", ascending=False), "\n")

print("Nominal:")
print(df["nominal"].describe().round(0), "\n")

print("5 pelanggan dengan transaksi terbanyak:")
print(df.groupby(["pelanggan_id", "nama"]).size().sort_values(ascending=False).head(5))
