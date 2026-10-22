# Cara naif: setiap sel diganti nilai acak baru, relasi antartabel putus
from faker import Faker

from data_csv import baca, hitung_transaksi_tersambung

Faker.seed(2026)
fake = Faker("id_ID")
pelanggan = baca("pelanggan.csv")
transaksi = baca("transaksi.csv")
print("Sebelum disamarkan, transaksi tersambung:", hitung_transaksi_tersambung(pelanggan, transaksi), "dari", len(transaksi))

for p in pelanggan:
    p["nama"] = fake.name()
    p["email"] = fake.free_email()
for t in transaksi:
    t["email_pelanggan"] = fake.free_email()

print("Sesudah disamarkan, transaksi tersambung:", hitung_transaksi_tersambung(pelanggan, transaksi), "dari", len(transaksi))
