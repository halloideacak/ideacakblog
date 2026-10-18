package com.ideacak.warteg;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// CommandLineRunner: method run() dijalankan Spring sekali, setelah semua object siap
@Component
public class DemoKasir implements CommandLineRunner {
    private final KasirService kasirService;

    public DemoKasir(KasirService kasirService) {
        this.kasirService = kasirService;
    }

    @Override
    public void run(String... args) {
        System.out.println();
        System.out.println("Daftar menu:");
        Menu[] daftarMenu = kasirService.getDaftarMenu();
        for (int i = 0; i < daftarMenu.length; i++) {
            Menu menu = daftarMenu[i];
            System.out.println((i + 1) + ". " + menu.getNama() + " (Rp" + menu.getHarga() + ", sisa " + menu.getStok() + ")");
        }

        System.out.println();
        cobaLayani("Budi", List.of(new ItemPesanan(2, 2), new ItemPesanan(4, 2)));
        cobaLayani("Siti", List.of(new ItemPesanan(3, 1), new ItemPesanan(4, 2)));
    }

    private void cobaLayani(String namaPembeli, List<ItemPesanan> pesanan) {
        try {
            System.out.println(kasirService.layani(namaPembeli, pesanan));
        } catch (PesananTidakValidException e) {
            System.out.println("Pesanan " + namaPembeli + " ditolak: " + e.getMessage());
            System.out.println();
        }
    }
}
