from fastapi import FastAPI
from faker import Faker


app = FastAPI()
Faker.seed(1404)
fake = Faker('id_ID')


@app.get("/transaction")
def get_transaction():
    transactions = []
    for _ in range(5):
        transactions.append({
            "id" : fake.uuid4(),
            "customer_name" : fake.name(),
            "amount": fake.random_int(1000, 150000),
            "date": fake.date_this_year()
        })
    return {
        "transactions": transactions
    }