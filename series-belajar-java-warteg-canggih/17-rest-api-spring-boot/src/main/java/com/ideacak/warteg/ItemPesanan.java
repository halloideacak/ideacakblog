package com.ideacak.warteg;

// satu baris pesanan dari pembeli, dikirim sebagai JSON: {"nomorMenu": 2, "porsi": 2}
public record ItemPesanan(int nomorMenu, int porsi) {
}
