package com.ideacak.warteg;

// satu baris pesanan: menu nomor sekian (mulai dari 1, seperti di daftar menu), sekian porsi
public class ItemPesanan {
    private final int nomorMenu;
    private final int porsi;

    public ItemPesanan(int nomorMenu, int porsi) {
        this.nomorMenu = nomorMenu;
        this.porsi = porsi;
    }

    public int getNomorMenu() {
        return nomorMenu;
    }

    public int getPorsi() {
        return porsi;
    }
}
