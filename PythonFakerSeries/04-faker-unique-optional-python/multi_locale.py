# Multi-locale: pelanggan lokal dan ekspatriat dalam satu generator
from collections import Counter, OrderedDict

from faker import Faker

Faker.seed(2026)
fake = Faker(["id_ID", "en_US"])
print(fake.locales)
print([fake.name() for _ in range(6)])

# akses langsung locale tertentu
print(fake["en_US"].name(), "|", fake["id_ID"].name())

# bobot locale: 9 banding 1
fake_berbobot = Faker(OrderedDict([("id_ID", 9), ("en_US", 1)]))
print(Counter(fake_berbobot.current_country() for _ in range(10000)))
