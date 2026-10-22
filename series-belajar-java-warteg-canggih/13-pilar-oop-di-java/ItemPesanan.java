// satu kali pembeli menyebut pesanan: menu nomor sekian, sekian porsi
class ItemPesanan {
    private final int indexMenu;
    private final int porsi;

    ItemPesanan(int indexMenu, int porsi) {
        this.indexMenu = indexMenu;
        this.porsi = porsi;
    }

    int getIndexMenu() {
        return indexMenu;
    }

    int getPorsi() {
        return porsi;
    }
}
