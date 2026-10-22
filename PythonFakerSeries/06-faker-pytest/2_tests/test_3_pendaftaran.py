# Validator versi perbaikan, diuji dengan 200 pendaftar berbeda
import re

import pytest

from data_uji import buat_pendaftar_realistis
from pendaftaran import normalisasi_telepon, validasi_pendaftaran


@pytest.mark.parametrize("nomor", range(200))
def test_pendaftar_realistis_lolos(faker, nomor):
    faker.seed_instance(nomor)
    data = buat_pendaftar_realistis(faker)
    assert validasi_pendaftaran(data) == [], data


def test_nama_bergelar_bawaan_faker(faker):
    daftar = [faker.name() for _ in range(2000)]
    bergelar = [n for n in daftar if re.search(r"[^A-Za-z ]", n)]
    print(f"\nnama dengan gelar atau tanda baca: {len(bergelar)} dari 2.000, contoh: {bergelar[:3]}")


def test_telepon_bawaan_faker(faker):
    daftar = [faker.phone_number() for _ in range(1000)]
    ditolak = [t for t in daftar if not normalisasi_telepon(t).startswith("08")]
    print(f"\nphone_number() yang bukan nomor seluler 08: {len(ditolak)} dari 1.000, contoh: {ditolak[:3]}")


def test_nik_beda_tanggal_ditolak(faker):
    data = buat_pendaftar_realistis(faker)
    data["nik"] = faker.nik(tanggal_lahir=faker.date_of_birth(minimum_age=17, maximum_age=80))
    if data["nik"][6:12] == faker.nik(tanggal_lahir=data["tanggal_lahir"])[6:12]:
        pytest.skip("kebetulan tanggal lahirnya sama")
    assert "tanggal lahir tidak sama dengan tanggal lahir di NIK" in validasi_pendaftaran(data)
