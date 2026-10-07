public class WartegCanggih {
    public static void main(String[] args) {
        final String NAMA_WARTEG = "Warteg Canggih";

        String menu1 = "Nasi putih";
        int harga1 = 5000;
        String menu2 = "Tempe orek";
        int harga2 = 4000;
        String menu3 = "Es teh";
        int harga3 = 3000;

        // pesanan dari catatan pelayan, tulisannya berantakan
        String pesanan = "  TEMPE orek ";
        String pesananRapi = pesanan.trim();
        boolean dikenali = pesananRapi.equalsIgnoreCase(menu2);
        System.out.println("Pesanan dikenali: " + dikenali);
        System.out.println();

        int total = harga1 + harga2 + harga3;
        String garis = "-".repeat(24);

        System.out.println(NAMA_WARTEG.toUpperCase());
        System.out.println(garis);
        System.out.println(String.format("%-14s%10s", menu1, "Rp" + harga1));
        System.out.println(String.format("%-14s%10s", menu2, "Rp" + harga2));
        System.out.println(String.format("%-14s%10s", menu3, "Rp" + harga3));
        System.out.println(garis);
        System.out.println(String.format("%-14s%10s", "TOTAL", "Rp" + total));
    }
}
