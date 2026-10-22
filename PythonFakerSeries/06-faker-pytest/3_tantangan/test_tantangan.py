# Jawaban tantangan artikel 6
import pytest

from data_uji import buat_pendaftar_realistis
from pendaftaran import validasi_pendaftaran


# Tantangan 1: pendaftar di bawah 17 tahun harus ditolak
@pytest.mark.parametrize("nomor", range(50))
def test_umur_di_bawah_17_ditolak(faker, nomor):
    faker.seed_instance(nomor)
    data = buat_pendaftar_realistis(faker)
    data["tanggal_lahir"] = faker.date_of_birth(minimum_age=10, maximum_age=16)
    data["nik"] = None
    assert "umur minimal 17 tahun" in validasi_pendaftaran(data)


# Tantangan 2: email tanpa @ harus ditolak
def test_email_tanpa_at_ditolak(faker):
    for _ in range(100):
        data = buat_pendaftar_realistis(faker)
        data["email"] = faker.user_name()
        assert "format email tidak valid" in validasi_pendaftaran(data), data["email"]


# Tantangan 3: buktikan seed-nya 2026 (lihat conftest.py)
def test_seed_2026(faker):
    print("\nseed 2026:", faker.name())
