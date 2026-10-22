# Membaca dan menulis CSV, dipakai oleh skrip 2 sampai 6
import csv


def baca(nama_file):
    with open(nama_file, encoding="utf-8") as file:
        return list(csv.DictReader(file))


def tulis(nama_file, data):
    with open(nama_file, "w", newline="", encoding="utf-8") as file:
        writer = csv.DictWriter(file, fieldnames=data[0].keys())
        writer.writeheader()
        writer.writerows(data)


def hitung_transaksi_tersambung(pelanggan, transaksi):
    daftar_email = {p["email"] for p in pelanggan}
    return sum(1 for t in transaksi if t["email_pelanggan"] in daftar_email)
