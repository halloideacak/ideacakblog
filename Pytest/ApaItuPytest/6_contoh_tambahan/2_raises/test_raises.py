import pytest

from nomor_hp import normalisasi_nomor_hp


def test_tanpa_raises():
    normalisasi_nomor_hp("0812")


def test_dengan_raises():
    with pytest.raises(ValueError):
        normalisasi_nomor_hp("0812")


def test_raises_tapi_tidak_error():
    with pytest.raises(ValueError):
        normalisasi_nomor_hp("0812-3456-7890")
