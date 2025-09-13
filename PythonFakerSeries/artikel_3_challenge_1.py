from faker.providers import BaseProvider
from datetime import datetime
from faker import Faker

class InsuranceCustomPoliceProvider(BaseProvider):
    def policy_number(self):
        timestamp = datetime.now().strftime("%Y%m%d%H%M%S")
        return f"POL-{timestamp}{self.generator.random_int(100000, 9999999)}"

fake = Faker("id_ID")
fake.add_provider(InsuranceCustomPoliceProvider)

print(fake.policy_number())