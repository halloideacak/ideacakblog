// induk semua pelanggaran aturan pesanan warteg
// checked (turunan Exception), karena kasir bisa memulihkan keadaan: tolak, lalu tanya ulang
class PesananTidakValidException extends Exception {
    PesananTidakValidException(String pesan) {
        super(pesan);
    }
}
