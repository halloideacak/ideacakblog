# Menyamarkan pelanggan.csv dan transaksi.csv, lalu memeriksa hasilnya
from data_csv import baca, hitung_transaksi_tersambung, tulis
from penyamar import samarkan_email, samarkan_nama, samarkan_telepon

pelanggan = baca("pelanggan.csv")
transaksi = baca("transaksi.csv")
email_asli = {p["email"] for p in pelanggan}
total_asli = sum(int(t["nominal"]) for t in transaksi)

for p in pelanggan:
    p["nama"] = samarkan_nama(p["nama"])
    p["email"] = samarkan_email(p["email"])
    p["telepon"] = samarkan_telepon(p["telepon"])
    # id dan kota dibiarkan, karena dipakai untuk analisis
for t in transaksi:
    t["email_pelanggan"] = samarkan_email(t["email_pelanggan"])

tulis("pelanggan_samaran.csv", pelanggan)
tulis("transaksi_samaran.csv", transaksi)

print(pelanggan[0])
print("Transaksi tersambung:", hitung_transaksi_tersambung(pelanggan, transaksi), "dari", len(transaksi))
print("Email samaran berbeda:", len({p["email"] for p in pelanggan}), "dari", len(pelanggan))
print("Email asli yang tersisa:", sum(1 for p in pelanggan if p["email"] in email_asli))
print("Total nominal sama:", sum(int(t["nominal"]) for t in transaksi) == total_asli)
