package com.ideacak.warteg;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

// logika kasir tanpa Scanner dan tanpa println:
// menerima pesanan sebagai data, mengembalikan struk sebagai String
@Service
public class KasirService {
    static final int PORSI_MAKSIMAL = 20;

    private final Menu[] daftarMenu;

    // Spring melihat constructor ini butuh ArsipWarteg, lalu mengirimkan object yang sudah dia buat
    public KasirService(ArsipWarteg arsip) throws IOException {
        this.daftarMenu = arsip.bacaMenu();
    }

    public Menu[] getDaftarMenu() {
        return daftarMenu;
    }

    public String layani(String namaPembeli, List<ItemPesanan> pesanan) throws PesananTidakValidException {
        int[] porsi = new int[daftarMenu.length];
        for (ItemPesanan item : pesanan) {
            int index = item.getNomorMenu() - 1;
            if (index < 0 || index >= daftarMenu.length) {
                throw new PesananTidakValidException("Menu nomor " + item.getNomorMenu() + " tidak ada.");
            }
            if (item.getPorsi() < 1 || item.getPorsi() > PORSI_MAKSIMAL) {
                throw new PesananTidakValidException("Jumlah porsi harus 1 sampai " + PORSI_MAKSIMAL + ".");
            }
            porsi[index] += item.getPorsi();
        }

        // cek semua stok dulu, baru dikurangi, supaya pesanan tidak tercatat setengah
        for (int i = 0; i < daftarMenu.length; i++) {
            Menu menu = daftarMenu[i];
            if (porsi[i] > menu.getStok()) {
                throw new StokTidakCukupException(menu.getNama(), porsi[i], menu.getStok());
            }
        }
        for (int i = 0; i < daftarMenu.length; i++) {
            if (porsi[i] > 0) {
                daftarMenu[i].kurangiStok(porsi[i]);
            }
        }

        int subtotal = 0;
        for (int i = 0; i < daftarMenu.length; i++) {
            subtotal += daftarMenu[i].hitungHarga(porsi[i]);
        }
        if (subtotal == 0) {
            throw new PesananTidakValidException("Pesanan kosong.");
        }
        int diskon = hitungDiskon(subtotal);
        return buatStruk(namaPembeli, porsi, subtotal, diskon, subtotal - diskon);
    }

    static int hitungDiskon(int subtotal) {
        if (subtotal >= 20000) {
            return 2000;
        }
        if (subtotal >= 10000) {
            return 1000;
        }
        return 0;
    }

    private String buatStruk(String namaPembeli, int[] porsi, int subtotal, int diskon, int total) {
        String struk = "Pesanan atas nama " + namaPembeli + "\n";
        for (int i = 0; i < daftarMenu.length; i++) {
            if (porsi[i] > 0) {
                Menu menu = daftarMenu[i];
                struk += String.format("%-14s%3s%11s\n", menu.getNama(), "x" + porsi[i], "Rp" + menu.hitungHarga(porsi[i]));
            }
        }
        struk += "-".repeat(28) + "\n";
        struk += String.format("%-17s%11s\n", "Subtotal", "Rp" + subtotal);
        struk += String.format("%-17s%11s\n", "Diskon", "Rp" + diskon);
        struk += String.format("%-17s%11s\n", "TOTAL", "Rp" + total);
        return struk;
    }
}
