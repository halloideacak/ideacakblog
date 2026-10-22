# Jebakan: name_male() di locale id_ID tidak menjamin nama laki-laki
from faker import Faker
from faker.providers.person.id_ID import Provider as PersonIndonesia

Faker.seed(2026)
fake = Faker("id_ID")

print([fake.name_male() for _ in range(4)])

nama_depan_perempuan = set(PersonIndonesia.first_names_female)
daftar_nama = [fake.name_male() for _ in range(1000)]
salah = [n for n in daftar_nama if any(kata in nama_depan_perempuan for kata in n.split()[:2])]
print("name_male() dengan nama depan perempuan:", len(salah), "dari 1.000")

# perbaikan: susun sendiri dari nama depan dan nama belakang laki-laki
print([f"{fake.first_name_male()} {fake.last_name_male()}" for _ in range(4)])
