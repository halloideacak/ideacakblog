import pytest

from nomor_hp import normalisasi_nomor_hp


@pytest.mark.parametrize(
    "nomor, hasil",
    [
        ("+62 812 3456 7890", "6281234567890"),
        ("6281234567890", "6281234567890"),
        ("0812-3456-7890", "6281234567890"),
        ("0812 3456 7890", "6281234567890"),
        ("0812345678", "62812345678"),
        ("+6285712345678", "6285712345678"),
    ],
)
def test_normalisasi(nomor, hasil):
    assert normalisasi_nomor_hp(nomor) == hasil


@pytest.mark.parametrize("nomor", ["0812", "", "0812-3456-7890-1234"])
def test_nomor_tidak_valid(nomor):
    with pytest.raises(ValueError):
        normalisasi_nomor_hp(nomor)
