# Cara manual: ingat sendiri email yang sudah keluar dengan set
from faker import Faker

Faker.seed(2026)
fake = Faker("id_ID")


def buat_email_unik(jumlah):
    sudah_dipakai = set()
    hasil = []
    while len(hasil) < jumlah:
        email = fake.free_email()
        if email in sudah_dipakai:
            continue                      # kembar, ambil ulang
        sudah_dipakai.add(email)
        hasil.append(email)
    return hasil


daftar_email = buat_email_unik(1000)
print(len(daftar_email), "email,", len(set(daftar_email)), "berbeda")
