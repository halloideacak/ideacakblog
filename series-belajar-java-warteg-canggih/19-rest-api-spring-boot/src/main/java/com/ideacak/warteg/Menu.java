package com.ideacak.warteg;

// sama seperti versi 14 dan 15: stok hanya bisa diubah lewat method yang menjaga aturannya
public class Menu {
    private final String nama;
    private final int harga;
    private int stok;

    public Menu(String nama, int harga, int stok) {
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }

    public String getNama() {
        return nama;
    }

    public int getHarga() {
        return harga;
    }

    public int getStok() {
        return stok;
    }

    int hitungHarga(int porsi) {
        return harga * porsi;
    }

    void kurangiStok(int porsi) throws StokTidakCukupException {
        if (porsi > stok) {
            throw new StokTidakCukupException(nama, porsi, stok);
        }
        stok -= porsi;
    }
}
