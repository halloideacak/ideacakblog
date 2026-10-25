DAFTAR_KONTAK = ["0812-3456-7890", "+62 857 1234 5678"]


def test_tambah_kontak():
    DAFTAR_KONTAK.append("0813-0000-1111")
    assert len(DAFTAR_KONTAK) == 3


def test_jumlah_kontak():
    assert len(DAFTAR_KONTAK) == 2
