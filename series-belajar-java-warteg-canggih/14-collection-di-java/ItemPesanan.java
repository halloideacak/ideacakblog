// satu kali pembeli menyebut pesanan: menu apa, sekian porsi
// sekarang menyimpan object Menu langsung, bukan nomor index-nya
class ItemPesanan {
    private final Menu menu;
    private final int porsi;

    ItemPesanan(Menu menu, int porsi) {
        this.menu = menu;
        this.porsi = porsi;
    }

    Menu getMenu() {
        return menu;
    }

    int getPorsi() {
        return porsi;
    }
}
