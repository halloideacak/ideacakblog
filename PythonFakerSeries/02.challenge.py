from faker import Faker
import csv, random

faker = Faker()

# --- Generate Customers ---
customers = []
for i in range(1, 101):  # 100 customers
    customers.append({
        "customer_id": i,
        "name": faker.name(),
        "email": faker.email(),
        "gender": random.choice(["M", "F"]),
        "birth_date": faker.date_of_birth(minimum_age=18, maximum_age=70)
    })

with open("customers.csv", "w", newline="") as f:
    writer = csv.DictWriter(f, fieldnames=customers[0].keys())
    writer.writeheader()
    writer.writerows(customers)

# --- Generate Transactions ---
transactions = []
for i in range(1, 501):  # 500 transactions
    transactions.append({
        "transaction_id": i,
        "customer_id": random.choice(customers)["customer_id"],
        "amount": round(random.uniform(10, 1000), 2),
        "date": faker.date_this_year()
    })

with open("transactions.csv", "w", newline="") as f:
    writer = csv.DictWriter(f, fieldnames=transactions[0].keys())
    writer.writeheader()
    writer.writerows(transactions)
