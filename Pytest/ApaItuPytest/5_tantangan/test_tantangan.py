import pytest

from nomor_hp import normalisasi_nomor_hp


@pytest.mark.parametrize(
    "nomor, hasil",
    [
        ("(0812) 3456-7890", "6281234567890"),
        ("+62 (812) 3456 7890", "6281234567890"),
    ],
)
def test_nomor_dengan_kurung(nomor, hasil):
    assert normalisasi_nomor_hp(nomor) == hasil


@pytest.mark.parametrize("nomor", ["0812-34S6-7890", "62812abc4567", "0812+3456+7890"])
def test_nomor_berisi_huruf_atau_simbol(nomor):
    with pytest.raises(ValueError, match="tidak valid"):
        normalisasi_nomor_hp(nomor)
