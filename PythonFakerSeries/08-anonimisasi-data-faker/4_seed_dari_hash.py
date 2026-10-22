# Kenapa hash() bawaan Python tidak bisa dipakai, dan kenapa HMAC bisa
import subprocess
import sys

from penyamar import samarkan_email, samarkan_nama

perintah = [sys.executable, "-c", "print(hash('dlestari@hotmail.com'))"]
print("hash() proses 1:", subprocess.run(perintah, capture_output=True, text=True).stdout.strip())
print("hash() proses 2:", subprocess.run(perintah, capture_output=True, text=True).stdout.strip())

print(samarkan_nama("Prayogo Irawan"), "|", samarkan_email("dlestari@hotmail.com"))
print(samarkan_nama("Prayogo Irawan"), "|", samarkan_email("dlestari@hotmail.com"))

# tanpa akhiran sidik, dua email asli bisa jatuh ke email palsu yang sama
from data_csv import baca
from penyamar import fake, pakai_seed_dari


def samarkan_email_polos(email):
    pakai_seed_dari(email, "email")
    return f"{fake.user_name()}@example.com"


daftar_email = [p["email"] for p in baca("pelanggan.csv")]
print("Tanpa akhiran:", len({samarkan_email_polos(e) for e in daftar_email}), "email berbeda dari", len(daftar_email))
print("Dengan akhiran:", len({samarkan_email(e) for e in daftar_email}), "email berbeda dari", len(daftar_email))
