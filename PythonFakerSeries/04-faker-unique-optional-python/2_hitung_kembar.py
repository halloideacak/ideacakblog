# Seberapa sering Faker menghasilkan nilai kembar?
from collections import Counter

from faker import Faker

Faker.seed(2026)
fake = Faker("id_ID")

nama_depan = [fake.first_name() for _ in range(500)]
print("Nama depan berbeda:", len(set(nama_depan)), "dari 500")

daftar_email = [fake.free_email() for _ in range(1000)]
kembar = {email: jumlah for email, jumlah in Counter(daftar_email).items() if jumlah > 1}
print("Email kembar dari 1.000:", kembar)
