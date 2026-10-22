import pytest

from nik_provider import NikProvider


@pytest.fixture(scope="session", autouse=True)
def faker_session_locale():
    return ["id_ID"]


# Tantangan 3: semua test di folder ini memakai seed 2026, bukan 0
@pytest.fixture(scope="session", autouse=True)
def faker_seed():
    return 2026


@pytest.fixture(autouse=True)
def tambah_provider(faker):
    faker.add_provider(NikProvider)
