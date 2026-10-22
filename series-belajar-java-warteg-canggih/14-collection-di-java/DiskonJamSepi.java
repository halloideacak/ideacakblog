// jam sepi: semua belanja dipotong 10 persen, berapa pun subtotalnya
class DiskonJamSepi implements AturanDiskon {
    @Override
    public int hitung(int subtotal) {
        return subtotal / 10;
    }
}
