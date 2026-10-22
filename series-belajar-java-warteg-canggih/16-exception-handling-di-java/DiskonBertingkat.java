// aturan lama sejak artikel Percabangan: makin besar belanja, makin besar potongan
class DiskonBertingkat implements AturanDiskon {
    @Override
    public int hitung(int subtotal) {
        if (subtotal >= 20000) {
            return 2000;
        }
        if (subtotal >= 10000) {
            return 1000;
        }
        return 0;
    }
}
