import pytest

from nik_provider import NikProvider


@pytest.fixture(scope="session", autouse=True)
def faker_session_locale():
    return ["id_ID"]


# Tantangan 3: semua test di folder ini memakai seed 2026, bukan 0
@pytest.fixture(scope="session", autouse=True)
def faker_seed():
    return 2026


# Tantangan 4: faker multi-locale menolak add_provider, jadi provider dipasang ke locale id_ID saja.
# Untuk faker satu locale, faker["id_ID"] adalah object faker itu sendiri.
@pytest.fixture(autouse=True)
def tambah_provider(faker):
    faker["id_ID"].add_provider(NikProvider)
