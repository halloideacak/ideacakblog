package com.ideacak.warteg;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// @Component: Spring yang membuat object ini, cukup satu untuk seluruh aplikasi
@Component
public class ArsipWarteg {
    private final Path folder;

    // nilai folder diambil dari application.properties, bukan ditulis di kode
    public ArsipWarteg(@Value("${warteg.folder-data}") String folder) {
        this.folder = Path.of(folder);
    }

    // format tiap baris menu.txt: nama;harga;stok (baris kosong dan baris berawalan # dilewati)
    public Menu[] bacaMenu() throws IOException {
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
        return Arrays.copyOf(hasil, jumlah);
    }
}
