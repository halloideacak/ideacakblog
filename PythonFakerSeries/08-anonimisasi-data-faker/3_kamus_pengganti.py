# Kamus pengganti: nilai asli yang sama selalu diganti nilai palsu yang sama, selama program berjalan
from faker import Faker

from data_csv import baca, hitung_transaksi_tersambung


def buat_penyamar():
    fake = Faker("id_ID")
    kamus = {}

    def samarkan_email(email):
        if email not in kamus:
            kamus[email] = fake.unique.free_email()
        return kamus[email]

    return samarkan_email, kamus


Faker.seed(2026)
pelanggan = baca("pelanggan.csv")
transaksi = baca("transaksi.csv")

samarkan_email, kamus = buat_penyamar()
for p in pelanggan:
    p["email"] = samarkan_email(p["email"])
for t in transaksi:
    t["email_pelanggan"] = samarkan_email(t["email_pelanggan"])
print("Transaksi tersambung:", hitung_transaksi_tersambung(pelanggan, transaksi), "dari", len(transaksi))
print("Isi kamus:", len(kamus), "pasangan")

# dijalankan ulang dengan penyamar baru: hasilnya berbeda
email_asli = baca("pelanggan.csv")[0]["email"]
samarkan_lagi, _ = buat_penyamar()
print(email_asli, "->", kamus[email_asli], "(jalan pertama)")
print(email_asli, "->", samarkan_lagi(email_asli), "(jalan kedua)")
