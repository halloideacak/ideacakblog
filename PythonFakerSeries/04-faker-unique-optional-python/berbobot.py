# Data berbobot: metode bayar tidak muncul sama rata
from collections import Counter, OrderedDict

from faker import Faker

Faker.seed(2026)
fake = Faker("id_ID")

tanpa_bobot = ["Tunai", "QRIS", "Transfer"]
print("Tanpa bobot:", Counter(fake.random_element(tanpa_bobot) for _ in range(10000)))

metode_bayar = OrderedDict([
    ("Tunai", 6),
    ("QRIS", 3),
    ("Transfer", 1),
])
print("Dengan bobot:", Counter(fake.random_element(metode_bayar) for _ in range(10000)))

# random_elements: ambil beberapa sekaligus, tetap mengikuti bobot
print(fake.random_elements(metode_bayar, length=5))
