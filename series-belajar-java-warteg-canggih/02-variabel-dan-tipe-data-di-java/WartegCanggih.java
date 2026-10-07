public class WartegCanggih {
    public static void main(String[] args) {
        final String NAMA_WARTEG = "Warteg Canggih";

        String menu1 = "Nasi putih";
        int harga1 = 5000;

        String menu2 = "Tempe orek";
        int harga2 = 4000;

        String menu3 = "Es teh";
        int harga3 = 3000;

        boolean bukaHariIni = true;

        System.out.println("Selamat datang di " + NAMA_WARTEG + "!");
        System.out.println("Buka hari ini: " + bukaHariIni);
        System.out.println();
        System.out.println("Daftar menu:");
        System.out.println(menu1 + ": Rp" + harga1);
        System.out.println(menu2 + ": Rp" + harga2);
        System.out.println(menu3 + ": Rp" + harga3);
    }
}
