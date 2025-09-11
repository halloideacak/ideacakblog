from faker import Faker
import pprint
from faker.providers import BaseProvider

fake = Faker('id_ID')

profile = fake.simple_profile()
pprint.pprint(profile)

full_profile = fake.profile()
pprint.pprint(full_profile)

class DoctorCodeProvider(BaseProvider):
    def doctor_code(self):
        return f"DR-{self.generator.random_int(1000, 9999)}"

fake.add_provider(DoctorCodeProvider)
print(fake.doctor_code())