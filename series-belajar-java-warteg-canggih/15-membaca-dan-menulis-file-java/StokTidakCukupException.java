// pelanggaran yang lebih spesifik: porsi yang diminta melebihi stok
class StokTidakCukupException extends PesananTidakValidException {
    private final int sisaStok;

    StokTidakCukupException(String namaMenu, int diminta, int sisaStok) {
        super("Stok " + namaMenu + " tinggal " + sisaStok + ", tidak cukup untuk " + diminta + " porsi.");
        this.sisaStok = sisaStok;
    }

    int getSisaStok() {
        return sisaStok;
    }
}
