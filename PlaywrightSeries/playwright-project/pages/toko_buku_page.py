from playwright.sync_api import Page, expect


class TokoBukuPage:
    """Page object buat halaman utama Toko Buku Kecil (index.html)."""

    def __init__(self, page: Page):
        self.page = page
        self.kotak_cari = page.get_by_label("Cari buku")
        self.tombol_tambah_keranjang = page.get_by_role("button", name="Add to cart")
        self.pesan_keranjang = page.get_by_text("Item added to cart")
        self.nama_input = page.get_by_label("Nama Lengkap")
        self.tanggal_lahir_input = page.get_by_label("Tanggal Lahir")
        self.setuju_checkbox = page.get_by_label("I agree to the terms above")
        self.submit_button = page.get_by_role("button", name="Submit")

    def buka(self):
        self.page.goto("/index.html")

    def cari_buku(self, judul):
        self.kotak_cari.press_sequentially(judul)

    def tambah_buku_pertama_ke_keranjang(self):
        self.tombol_tambah_keranjang.first.click()

    def pastikan_notifikasi_keranjang_muncul(self):
        expect(self.pesan_keranjang).to_be_visible()

    def isi_checkout(self, nama, tanggal_lahir):
        self.nama_input.fill(nama)
        self.tanggal_lahir_input.fill(tanggal_lahir)
        self.setuju_checkbox.check()

    def submit_checkout(self):
        self.submit_button.click()
