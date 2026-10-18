package com.ideacak.warteg;

// bentuk JSON saat permintaan ditolak: {"pesan": "..."}
public record PesanError(String pesan) {
}
