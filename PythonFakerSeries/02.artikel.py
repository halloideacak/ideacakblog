from faker import Faker
import uuid
import pprint
import csv
import json


fake = Faker('id_ID')

##supaya konsisten
Faker.seed(42) 

def generate_customer():
    return {
        "id": str(uuid.uuid4()),
        "name": fake.name(),
        "email": fake.email(),
        "phone": fake.phone_number(),
        "address": fake.address(),
        "registered_date": str(fake.date_this_decade())
    }

# Contoh satu customer
dummy_customer = generate_customer()

## keperluan print biar cakep
pprint.pprint(dummy_customer)

## buat 100 customer
customers = [generate_customer() for _ in range(100)]

# Menentukan nama field
fieldnames = ["id", "name", "email", "phone", "address", "registered_date"]

# Menyimpan ke file
# with open("customers.csv", "w", newline="", encoding="utf-8") as file:
#     writer = csv.DictWriter(file, fieldnames=fieldnames)
#     writer.writeheader()
#     for customer in customers:
#         writer.writerow(customer)

# with open("customers.json", "w", encoding="utf-8") as file:
#     json.dump(customers, file, ensure_ascii=False, indent=4)