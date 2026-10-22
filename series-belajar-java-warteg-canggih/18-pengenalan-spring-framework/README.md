# 16. Pengenalan Spring Framework

Bagian dari **Seri Belajar Pemrograman Java Studi Kasus Warteg Canggih** di [ideacak.com](https://www.ideacak.com/search/label/Warteg%20Canggih%20Series).

Proyek Spring Boot pertama kasir Warteg Canggih. Logika kasir dipindah ke `KasirService`, yang menerima pesanan sebagai data dan mengembalikan struk sebagai `String`. Spring yang membuat dan menyambungkan object-nya lewat dependency injection: `DemoKasir` butuh `KasirService`, dan `KasirService` butuh `ArsipWarteg`. Belum ada Scanner maupun web. `DemoKasir` hanya mencoba dua pesanan contoh lalu aplikasi berhenti.

Kerangka proyek dibuat dari [Spring Initializr](https://start.spring.io) dengan pilihan: Maven, Java, Spring Boot 4.1.1, group `com.ideacak`, artifact `warteg-canggih`, Java 21, tanpa dependensi tambahan.

## Isi Proyek

- `pom.xml`: pengaturan Maven, dependensi `spring-boot-starter`.
- `mvnw`, `mvnw.cmd`, `.mvn/`: Maven Wrapper, mengunduh Maven otomatis.
- `data/menu.txt`: daftar menu dan stok, format `nama;harga;stok`.
- `src/main/resources/application.properties`: lokasi folder data (`warteg.folder-data`).
- `src/main/java/com/ideacak/warteg/`
  - `WartegCanggihApplication.java`: titik awal aplikasi (`@SpringBootApplication`).
  - `ArsipWarteg.java`: membaca `menu.txt` (`@Component`).
  - `KasirService.java`: validasi pesanan, stok, diskon, dan struk (`@Service`).
  - `DemoKasir.java`: mencoba dua pesanan saat aplikasi siap (`CommandLineRunner`).
  - `Menu.java`, `ItemPesanan.java`, `PesananTidakValidException.java`, `StokTidakCukupException.java`.

## Kebutuhan

- JDK 21 (misalnya [Eclipse Temurin 21](https://adoptium.net/temurin/releases)). Spring Boot 4.1.1 butuh minimal Java 17.
- Koneksi internet saat pertama kali dijalankan, untuk mengunduh Maven dan dependensi Spring.
- `JAVA_HOME` harus menunjuk ke JDK 21. Kalau Maven gagal dengan pesan `release version 21 not supported`, berarti `JAVA_HOME` masih menunjuk ke JDK lama.

## Cara Menjalankan

Dari folder ini (folder yang berisi `pom.xml` dan folder `data`):

```
./mvnw spring-boot:run
```

Di Windows Command Prompt:

```
mvnw spring-boot:run
```

Atau buat file jar dulu, lalu jalankan:

```
./mvnw package
java -jar target/warteg-canggih-0.0.1-SNAPSHOT.jar
```

## Output

Setelah logo Spring dan beberapa baris log:

```
Daftar menu:
1. Nasi putih (Rp5000, sisa 50)
2. Tempe orek (Rp4000, sisa 20)
3. Es teh (Rp3000, sisa 40)
4. Telur balado (Rp6000, sisa 3)
5. Sayur asem (Rp4000, sisa 0)

Pesanan atas nama Budi
Tempe orek     x2     Rp8000
Telur balado   x2    Rp12000
----------------------------
Subtotal             Rp20000
Diskon                Rp2000
TOTAL                Rp18000

Pesanan Siti ditolak: Stok Telur balado tinggal 1, tidak cukup untuk 2 porsi.
```

Versi ini tidak menulis apa pun ke `data/menu.txt`, jadi stok kembali seperti semula setiap aplikasi dijalankan.
