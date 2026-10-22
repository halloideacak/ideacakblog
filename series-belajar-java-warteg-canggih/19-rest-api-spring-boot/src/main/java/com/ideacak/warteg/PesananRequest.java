package com.ideacak.warteg;

import java.util.List;

// isi body POST /api/pesanan
public record PesananRequest(String namaPembeli, List<ItemPesanan> pesanan) {
}
