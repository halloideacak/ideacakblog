import pytest


@pytest.fixture
def daftar_kontak():
    return ["0812-3456-7890", "+62 857 1234 5678"]


def test_tambah_kontak(daftar_kontak):
    daftar_kontak.append("0813-0000-1111")
    assert len(daftar_kontak) == 3


def test_jumlah_kontak(daftar_kontak):
    assert len(daftar_kontak) == 2
