import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

// semua urusan file dikumpulkan di sini, supaya Kasir tidak perlu tahu letak dan format file-nya
class ArsipWarteg {
    private final Path folder;

    ArsipWarteg(Path folder) {
        this.folder = folder;
    }

    // format tiap baris menu.txt: nama;harga;stok (baris kosong dan baris berawalan # dilewati)
    Menu[] bacaMenu() throws IOException {
        List<String> semuaBaris = Files.readAllLines(folder.resolve("menu.txt"));
        Menu[] hasil = new Menu[semuaBaris.size()];
        int jumlah = 0;
        int nomorBaris = 0;

        for (String baris : semuaBaris) {
            nomorBaris++;
            baris = baris.trim();
            if (baris.isEmpty() || baris.startsWith("#")) {
                continue;
            }
            String[] bagian = baris.split(";");
            if (bagian.length != 3) {
                System.out.println("menu.txt baris " + nomorBaris + " dilewati: formatnya harus nama;harga;stok");
                continue;
            }
            try {
                int harga = Integer.parseInt(bagian[1].trim());
                int stok = Integer.parseInt(bagian[2].trim());
                hasil[jumlah] = new Menu(bagian[0].trim(), harga, stok);
                jumlah++;
            } catch (NumberFormatException e) {
                System.out.println("menu.txt baris " + nomorBaris + " dilewati: harga dan stok harus angka");
            }
        }
        return Arrays.copyOf(hasil, jumlah);      // buang sekat yang tidak terisi
    }

    // menimpa menu.txt dengan stok terbaru, jadi stok besok melanjutkan sisa hari ini
    void simpanMenu(Menu[] daftarMenu) throws IOException {
        String isi = "# nama;harga;stok\n";
        for (Menu menu : daftarMenu) {
            isi += menu.getNama() + ";" + menu.getHarga() + ";" + menu.getStok() + "\n";
        }
        Files.writeString(folder.resolve("menu.txt"), isi);
    }

    // satu file struk per hari, setiap struk baru ditambahkan di akhir file
    void tambahStruk(String struk) throws IOException {
        Path file = folder.resolve("struk-" + LocalDate.now() + ".txt");
        Files.writeString(file, struk + "\n", StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    void tambahRekap(int jumlahPembeli, int omzet) throws IOException {
        String baris = LocalDate.now() + ";" + jumlahPembeli + ";" + omzet + "\n";
        Files.writeString(folder.resolve("rekap.txt"), baris, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }
}
