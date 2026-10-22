package com.ideacak.warteg;

import java.util.List;

// struk sebagai data, bukan lagi teks yang sudah dirapikan:
// yang menampilkan (aplikasi HP, web, atau terminal) bebas mengatur tampilannya sendiri
public record Struk(String namaPembeli, List<Baris> pesanan, int subtotal, int diskon, int total) {

    public record Baris(String menu, int porsi, int harga) {
    }
}
