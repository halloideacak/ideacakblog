// satu object Menu = satu baris di papan menu
// field private: dari luar class, harga hanya bisa dibaca lewat getter dan diubah lewat ubahHarga
class Menu {
    private final String nama;
    private int harga;

    Menu(String nama, int harga) {
        this.nama = nama;
        this.harga = harga;
    }

    String getNama() {
        return nama;
    }

    int getHarga() {
        return harga;
    }

    // satu-satunya pintu untuk mengubah harga, dan pintu ini punya aturan
    boolean ubahHarga(int hargaBaru) {
        if (hargaBaru <= 0) {
            return false;
        }
        harga = hargaBaru;
        return true;
    }

    int hitungHarga(int porsi) {
        return harga * porsi;
    }

    void tampilkan(int nomor) {
        System.out.println(nomor + ". " + nama + " (Rp" + harga + ")");
    }
}
