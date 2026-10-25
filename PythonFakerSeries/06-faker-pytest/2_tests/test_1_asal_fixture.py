# Fixture faker sama dengan Faker("id_ID") yang di-seed 0, plus dua fixture pengatur lainnya
import pytest
from faker import Faker


def test_fixture_sama_dengan_faker_manual(faker):
    fake = Faker("id_ID")
    fake.seed_instance(0)

    nama_fixture = faker.name()
    nama_manual = fake.name()
    print("\nfixture:", faker.locales, nama_fixture)
    print("manual :", fake.locales, nama_manual)
    assert nama_fixture == nama_manual


@pytest.fixture
def faker_seed():
    return 2026


@pytest.fixture
def faker_locale():
    return ["de_DE"]


def test_seed_lain(faker, faker_seed):
    print("\nfaker_seed  :", faker.locales, faker.name())


def test_locale_lain(faker, faker_locale):
    print("\nfaker_locale:", faker.locales, faker.name())
