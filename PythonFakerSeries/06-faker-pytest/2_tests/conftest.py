import pytest

from nik_provider import NikProvider


@pytest.fixture(scope="session", autouse=True)
def faker_session_locale():
    return ["id_ID"]


@pytest.fixture(autouse=True)
def tambah_provider(faker):
    faker.add_provider(NikProvider)
