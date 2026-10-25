import pytest

from nomor_hp import normalisasi_nomor_hp


def test_format_internasional():
    assert normalisasi_nomor_hp("+62 812 3456 7890") == "6281234567890"


def test_sudah_format_62():
    assert normalisasi_nomor_hp("6281234567890") == "6281234567890"


def test_awalan_nol():
    assert normalisasi_nomor_hp("0812-3456-7890") == "6281234567890"


def test_nomor_terlalu_pendek():
    with pytest.raises(ValueError, match="tidak valid"):
        normalisasi_nomor_hp("0812")
