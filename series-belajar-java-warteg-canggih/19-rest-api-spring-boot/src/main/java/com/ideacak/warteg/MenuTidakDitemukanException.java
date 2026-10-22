package com.ideacak.warteg;

// unchecked: alamat yang salah bukan aturan bisnis, cukup dijawab 404
public class MenuTidakDitemukanException extends RuntimeException {
    public MenuTidakDitemukanException(int nomor) {
        super("Menu nomor " + nomor + " tidak ada.");
    }
}
