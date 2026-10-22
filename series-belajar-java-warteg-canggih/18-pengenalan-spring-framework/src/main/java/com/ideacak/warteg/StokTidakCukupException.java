package com.ideacak.warteg;

// pelanggaran yang lebih spesifik: porsi yang diminta melebihi stok
public class StokTidakCukupException extends PesananTidakValidException {
    private final int sisaStok;

    public StokTidakCukupException(String namaMenu, int diminta, int sisaStok) {
        super("Stok " + namaMenu + " tinggal " + sisaStok + ", tidak cukup untuk " + diminta + " porsi.");
        this.sisaStok = sisaStok;
    }

    public int getSisaStok() {
        return sisaStok;
    }
}
