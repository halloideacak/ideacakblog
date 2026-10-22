# Cara naif: transaksi dengan waktu dan nominal yang acak merata
from datetime import datetime

import pandas as pd
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
df = pd.DataFrame(transaksi)

print(df["waktu"].dt.hour.value_counts().sort_index().loc[[0, 3, 7, 12, 19, 23]])
print(df["nominal"].describe().round(0))
