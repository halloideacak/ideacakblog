import pytest


@pytest.mark.parametrize(
    "nama, nomor, umur",
    [
        ("Budi", "0812-3456-7890", 30),
        ("Sari", "+62 857 1234 5678", 25),
    ],
)
def test_tiga_parameter(nama, nomor, umur):
    assert umur > 17


@pytest.mark.parametrize(
    "kontak",
    [
        {"nama": "Budi", "nomor": ["0812-3456-7890", "0857-1111-2222"]},
        {"nama": "Sari", "nomor": ["+62 857 1234 5678"]},
    ],
    ids=["budi-dua-nomor", "sari-satu-nomor"],
)
def test_data_nested(kontak):
    assert len(kontak["nomor"]) >= 1


@pytest.mark.parametrize("operator", ["0812", "0857"])
@pytest.mark.parametrize("akhiran", ["34567890", "1234567"])
def test_kombinasi(operator, akhiran):
    assert (operator + akhiran).isdigit()
