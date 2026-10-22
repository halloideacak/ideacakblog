# Jawaban tantangan artikel 8 (jalankan 1_buat_data_contoh.py dan 5_samarkan_csv.py dulu)
import importlib
import os
from collections import Counter

import penyamar
from data_csv import baca

# Tantangan 1: kunci berbeda menghasilkan samaran berbeda
print("Kunci contoh :", penyamar.samarkan_email("dlestari@hotmail.com"))
os.environ["KUNCI_SAMARAN"] = "kunci-lain"
importlib.reload(penyamar)
print("Kunci lain   :", penyamar.samarkan_email("dlestari@hotmail.com"))


# Tantangan 2: omzet per kota sama persis sebelum dan sesudah disamarkan
def omzet_per_kota(file_pelanggan, file_transaksi):
    kota_per_email = {p["email"]: p["kota"] for p in baca(file_pelanggan)}
    omzet = Counter()
    for t in baca(file_transaksi):
        omzet[kota_per_email[t["email_pelanggan"]]] += int(t["nominal"])
    return omzet


asli = omzet_per_kota("pelanggan.csv", "transaksi.csv")
samaran = omzet_per_kota("pelanggan_samaran.csv", "transaksi_samaran.csv")
print("Omzet per kota sama:", asli == samaran, dict(asli.most_common(2)))

# Tantangan 3: tidak ada nama, email, atau telepon asli yang tertinggal di baris miliknya
asli_per_id = {p["id"]: p for p in baca("pelanggan.csv")}
bocor = [
    (s["id"], kolom)
    for s in baca("pelanggan_samaran.csv")
    for kolom in ("nama", "email", "telepon")
    if s[kolom] == asli_per_id[s["id"]][kolom]
]
print("Nilai asli yang tertinggal di barisnya sendiri:", len(bocor))

# kecocokan lintas baris: nilai samaran kebetulan sama dengan nilai asli milik orang lain
nilai_asli = {p[kolom]: p["id"] for p in asli_per_id.values() for kolom in ("nama", "email", "telepon")}
for s in baca("pelanggan_samaran.csv"):
    for kolom in ("nama", "email", "telepon"):
        if s[kolom] in nilai_asli:
            print(f"Kebetulan sama: {kolom} samaran id {s['id']} = {s[kolom]!r}, nilai asli milik id {nilai_asli[s[kolom]]}")
