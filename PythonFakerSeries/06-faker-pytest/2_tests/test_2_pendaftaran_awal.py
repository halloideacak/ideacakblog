# Test dengan data Faker: bug di validator versi awal mulai ketahuan
import pytest

from data_uji import buat_pendaftar
from pendaftaran_awal import validasi_pendaftaran


def test_pendaftar_faker_lolos(faker):
    for _ in range(100):
        data = buat_pendaftar(faker)
        assert validasi_pendaftaran(data) == [], data


@pytest.mark.parametrize("nomor", range(3))
def test_parametrize_tanpa_seed(faker, nomor):
    print(f"\nnomor {nomor}:", faker.name())
