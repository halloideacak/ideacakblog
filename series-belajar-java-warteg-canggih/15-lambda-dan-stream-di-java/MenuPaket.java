// paket hemat adalah menu juga (inheritance), ditambah daftar isi paketnya
class MenuPaket extends Menu {
    private final String[] isi;

    MenuPaket(String nama, int harga, String[] isi) {
        super(nama, harga);                       // nama dan harga tetap diurus oleh Menu
        this.isi = isi;
    }

    // tampilan paket berbeda dari menu biasa (polymorphism)
    @Override
    void tampilkan(int nomor) {
        super.tampilkan(nomor);
        System.out.println("   isi: " + String.join(", ", isi));
    }
}
