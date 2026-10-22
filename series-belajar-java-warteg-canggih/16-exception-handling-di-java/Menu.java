// satu object Menu = satu baris di papan menu: nama dan harga selalu satu paket
class Menu {
    String nama;
    int harga;

    Menu(String nama, int harga) {
        this.nama = nama;
        this.harga = harga;
    }

    int hitungHarga(int porsi) {
        return harga * porsi;
    }

    void tampilkan(int nomor) {
        System.out.println(nomor + ". " + nama + " (Rp" + harga + ")");
    }
}
