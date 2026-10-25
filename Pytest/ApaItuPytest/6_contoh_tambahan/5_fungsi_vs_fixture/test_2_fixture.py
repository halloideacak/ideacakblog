from pathlib import Path

import pytest


@pytest.fixture
def file_kontak():
    path = Path("kontak_fixture.txt")
    path.write_text("0812-3456-7890\n+62 857 1234 5678\n")
    yield path
    path.unlink()                   # selalu dijalankan, walaupun test gagal
    print("\n[fixture] file dihapus")


def test_baca_kontak(file_kontak):
    baris = file_kontak.read_text().splitlines()
    assert len(baris) == 3          # sengaja salah
