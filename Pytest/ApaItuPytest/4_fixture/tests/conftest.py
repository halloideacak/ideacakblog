import pytest

from nomor_hp import normalisasi_nomor_hp


@pytest.fixture(scope="session")
def daftar_kontak():
    print("\n[fixture] menyiapkan daftar kontak")
    return [
        {"nama": "Budi", "nomor": "0812-3456-7890"},
        {"nama": "Sari", "nomor": "+62 857 1234 5678"},
        {"nama": "Budi (kantor)", "nomor": "+62 812 3456 7890"},
    ]


@pytest.fixture
def nomor_bersih(daftar_kontak):
    return [normalisasi_nomor_hp(k["nomor"]) for k in daftar_kontak]


@pytest.fixture(autouse=True)
def penanda_test():
    print("\n[autouse] test dimulai")
