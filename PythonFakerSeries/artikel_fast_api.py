from fastapi import FastAPI
from faker import Faker

app = FastAPI()
fake = Faker('id_ID')

@app.get("/user")
def get_user():
    return {
        "name": fake.name(),
        "email": fake.email(),
        "address": fake.address()
    }