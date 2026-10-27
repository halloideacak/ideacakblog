# Cara naif: transaksi dengan waktu dan nominal yang acak merata
import statistics
from collections import Counter
from datetime import datetime

from faker import Faker

Faker.seed(2026)
fake = Faker("id_ID")

transaksi = [
    {
        "waktu": fake.date_time_between(start_date=datetime(2026, 9, 1), end_date=datetime(2026, 10, 1)),
        "nominal": fake.random_int(1000, 1_000_000),
    }
    for _ in range(20000)
]

per_jam = Counter(t["waktu"].hour for t in transaksi)
for jam in [0, 3, 7, 12, 19, 23]:
    print(f"Jam {jam:2}: {per_jam[jam]} transaksi")

daftar_nominal = [t["nominal"] for t in transaksi]
print(f"Rata-rata nominal: Rp{statistics.mean(daftar_nominal):,.0f}".replace(",", "."))
print(f"Median nominal   : Rp{statistics.median(daftar_nominal):,.0f}".replace(",", "."))
