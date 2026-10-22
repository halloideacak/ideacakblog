# Memasukkan 20.000 transaksi: commit per baris vs executemany dalam satu transaksi
import time
from pathlib import Path

from faker import Faker

from toko import buat_pelanggan, buat_transaksi, buka_database

Faker.seed(2026)
fake = Faker("id_ID")
pelanggan = buat_pelanggan(fake, 1000)
transaksi = buat_transaksi(fake, [p[0] for p in pelanggan], 20000)


def siapkan(path):
    Path(path).unlink(missing_ok=True)
    koneksi = buka_database(path)
    with koneksi:
        koneksi.executemany("INSERT INTO pelanggan VALUES (?, ?, ?, ?)", pelanggan)
    return koneksi


# cara lambat: satu INSERT, satu commit
koneksi = siapkan("lambat.db")
mulai = time.perf_counter()
for baris in transaksi:
    koneksi.execute("INSERT INTO transaksi VALUES (?, ?, ?, ?, ?)", baris)
    koneksi.commit()
print(f"Commit per baris : {time.perf_counter() - mulai:.2f} detik")
koneksi.close()

# cara cepat: semua baris dalam satu transaksi
koneksi = siapkan("cepat.db")
mulai = time.perf_counter()
with koneksi:
    koneksi.executemany("INSERT INTO transaksi VALUES (?, ?, ?, ?, ?)", transaksi)
print(f"executemany      : {time.perf_counter() - mulai:.2f} detik")
koneksi.close()

Path("lambat.db").unlink()
Path("cepat.db").unlink()
