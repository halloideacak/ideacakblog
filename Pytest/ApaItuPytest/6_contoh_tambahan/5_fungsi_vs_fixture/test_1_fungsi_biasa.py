from pathlib import Path


def siapkan_file_kontak():
    path = Path("kontak_fungsi.txt")
    path.write_text("0812-3456-7890\n+62 857 1234 5678\n")
    return path


def test_baca_kontak():
    path = siapkan_file_kontak()
    baris = path.read_text().splitlines()
    assert len(baris) == 3          # sengaja salah
    path.unlink()                   # bersih-bersih, tidak pernah sampai sini
