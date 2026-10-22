package com.ideacak.warteg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

// logika kasir: menerima pesanan sebagai data, mengembalikan Struk sebagai data
@Service
public class KasirService {
    static final int PORSI_MAKSIMAL = 20;

    private final Menu[] daftarMenu;

    public KasirService(ArsipWarteg arsip) throws IOException {
        this.daftarMenu = arsip.bacaMenu();
    }

    public List<MenuResponse> getDaftarMenu() {
        List<MenuResponse> hasil = new ArrayList<>();
        for (int i = 0; i < daftarMenu.length; i++) {
            hasil.add(keResponse(i));
        }
        return hasil;
    }

    public MenuResponse getMenu(int nomor) {
        int index = nomor - 1;
        if (index < 0 || index >= daftarMenu.length) {
            throw new MenuTidakDitemukanException(nomor);
        }
        return keResponse(index);
    }

    // synchronized: kalau dua pembeli memesan bersamaan, yang kedua menunggu yang pertama selesai,
    // supaya stok tidak dihitung dua kali
    public synchronized Struk layani(PesananRequest request) throws PesananTidakValidException {
        String namaPembeli = request.namaPembeli();
        if (namaPembeli == null || namaPembeli.isBlank()) {
            namaPembeli = "Pembeli";
        }
        if (request.pesanan() == null || request.pesanan().isEmpty()) {
            throw new PesananTidakValidException("Pesanan kosong.");
        }

        int[] porsi = new int[daftarMenu.length];
        for (ItemPesanan item : request.pesanan()) {
            int index = item.nomorMenu() - 1;
            if (index < 0 || index >= daftarMenu.length) {
                throw new PesananTidakValidException("Menu nomor " + item.nomorMenu() + " tidak ada.");
            }
            if (item.porsi() < 1 || item.porsi() > PORSI_MAKSIMAL) {
                throw new PesananTidakValidException("Jumlah porsi harus 1 sampai " + PORSI_MAKSIMAL + ".");
            }
            porsi[index] += item.porsi();
        }

        // cek semua stok dulu, baru dikurangi, supaya pesanan tidak tercatat setengah
        for (int i = 0; i < daftarMenu.length; i++) {
            Menu menu = daftarMenu[i];
            if (porsi[i] > menu.getStok()) {
                throw new StokTidakCukupException(menu.getNama(), porsi[i], menu.getStok());
            }
        }

        List<Struk.Baris> baris = new ArrayList<>();
        int subtotal = 0;
        for (int i = 0; i < daftarMenu.length; i++) {
            if (porsi[i] > 0) {
                Menu menu = daftarMenu[i];
                menu.kurangiStok(porsi[i]);
                int harga = menu.hitungHarga(porsi[i]);
                baris.add(new Struk.Baris(menu.getNama(), porsi[i], harga));
                subtotal += harga;
            }
        }
        int diskon = hitungDiskon(subtotal);
        return new Struk(namaPembeli, baris, subtotal, diskon, subtotal - diskon);
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

    private MenuResponse keResponse(int index) {
        Menu menu = daftarMenu[index];
        return new MenuResponse(index + 1, menu.getNama(), menu.getHarga(), menu.getStok());
    }
}
