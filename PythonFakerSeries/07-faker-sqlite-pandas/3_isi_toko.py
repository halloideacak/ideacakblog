# Isi toko.db dengan 1.000 pelanggan dan 20.000 transaksi, lalu cek relasinya
import sqlite3
from pathlib import Path

from toko import isi_database

Path("toko.db").unlink(missing_ok=True)
koneksi = isi_database("toko.db")

print("Pelanggan:", koneksi.execute("SELECT COUNT(*) FROM pelanggan").fetchone()[0])
print("Transaksi:", koneksi.execute("SELECT COUNT(*) FROM transaksi").fetchone()[0])
print(koneksi.execute("SELECT * FROM transaksi LIMIT 3").fetchall())

# foreign key aktif: transaksi untuk pelanggan yang tidak ada ditolak
try:
    with koneksi:
        koneksi.execute("INSERT INTO transaksi VALUES (20001, 9999, '2026-09-30 12:00:00', 15000, 'Tunai')")
except sqlite3.IntegrityError as e:
    print("IntegrityError:", e)
koneksi.close()
