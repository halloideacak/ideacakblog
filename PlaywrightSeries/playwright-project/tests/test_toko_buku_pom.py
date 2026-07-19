from pages.toko_buku_page import TokoBukuPage


def test_tambah_ke_keranjang(page):
    toko = TokoBukuPage(page)
    toko.buka()
    toko.tambah_buku_pertama_ke_keranjang()
    toko.pastikan_notifikasi_keranjang_muncul()


def test_checkout(page):
    toko = TokoBukuPage(page)
    toko.buka()
    toko.isi_checkout("Budi Santoso", "2020-02-02")
    toko.submit_checkout()
