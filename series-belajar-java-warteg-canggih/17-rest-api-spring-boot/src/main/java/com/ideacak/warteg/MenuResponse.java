package com.ideacak.warteg;

// bentuk JSON satu menu yang dikirim ke pembeli
public record MenuResponse(int nomor, String nama, int harga, int stok) {
}
