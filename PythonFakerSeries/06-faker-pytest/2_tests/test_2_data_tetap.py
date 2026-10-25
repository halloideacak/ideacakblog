# Test dengan satu data tetap: lolos, padahal validatornya masih punya bug
from datetime import date

from pendaftaran_awal import validasi_pendaftaran


def test_pendaftaran_budi_lolos():
    data = {
        "nama": "Budi Santoso",
        "email": "budi@example.com",
        "telepon": "081234567890",
        "tanggal_lahir": date(1998, 5, 15),
    }
    assert validasi_pendaftaran(data) == []
