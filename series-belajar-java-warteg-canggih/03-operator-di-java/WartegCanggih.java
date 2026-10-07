public class WartegCanggih {
    public static void main(String[] args) {
        final String NAMA_WARTEG = "Warteg Canggih";

        String menu1 = "Nasi putih";
        int harga1 = 5000;
        String menu2 = "Tempe orek";
        int harga2 = 4000;
        String menu3 = "Es teh";
        int harga3 = 3000;

        int jumlahNasi = 2;
        int uangPembeli = 20000;
        boolean pembeliMember = true;
        int jumlahOrang = 3;

        // aritmatika: perkalian dihitung lebih dulu daripada penjumlahan
        int total = harga1 * jumlahNasi + harga2 + harga3;
        int kembalian = uangPembeli - total;

        // perbandingan dan logika: hasilnya selalu true atau false
        boolean uangCukup = uangPembeli >= total;
        boolean dapatDiskon = total >= 15000 && pembeliMember;

        // pembagian int membuang desimal, casting ke double menyimpannya
        int patunganInt = total / jumlahOrang;
        double patunganDouble = (double) total / jumlahOrang;

        System.out.println("Selamat datang di " + NAMA_WARTEG + "!");
        System.out.println(menu1 + " x" + jumlahNasi + ": Rp" + (harga1 * jumlahNasi));
        System.out.println(menu2 + ": Rp" + harga2);
        System.out.println(menu3 + ": Rp" + harga3);
        System.out.println("Total: Rp" + total);
        System.out.println();
        System.out.println("Uang pembeli: Rp" + uangPembeli);
        System.out.println("Uang cukup: " + uangCukup);
        System.out.println("Kembalian: Rp" + kembalian);
        System.out.println("Dapat diskon member: " + dapatDiskon);
        System.out.println();
        System.out.println("Patungan " + jumlahOrang + " orang (int): Rp" + patunganInt);
        System.out.println("Patungan " + jumlahOrang + " orang (double): Rp" + patunganDouble);
    }
}
