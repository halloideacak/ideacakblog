package com.ideacak.warteg;

// induk semua pelanggaran aturan pesanan warteg (checked)
public class PesananTidakValidException extends Exception {
    public PesananTidakValidException(String pesan) {
        super(pesan);
    }
}
