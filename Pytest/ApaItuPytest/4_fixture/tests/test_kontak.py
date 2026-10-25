def test_jumlah_kontak(daftar_kontak):
    assert len(daftar_kontak) == 3


def test_semua_nomor_format_62(nomor_bersih):
    assert all(nomor.startswith("62") for nomor in nomor_bersih)


def test_nomor_kembar_ketahuan(nomor_bersih):
    assert len(set(nomor_bersih)) == 2
