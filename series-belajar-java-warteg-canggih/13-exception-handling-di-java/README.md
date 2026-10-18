# 13. Exception Handling di Java: try, catch, finally

Bagian dari **Seri Belajar Pemrograman Java Studi Kasus Warteg Canggih** di [ideacak.com](https://www.ideacak.com/search/label/Warteg%20Canggih%20Series).

Kasir membaca input per baris dan memakai `try-catch` untuk menangkap `NumberFormatException`, jadi trik "buang sisa Enter" tidak dibutuhkan lagi. Menu utama dibungkus `try-catch-finally` supaya rekap harian tetap dicetak walaupun input terhenti mendadak, dan Scanner ditutup dengan try-with-resources.

- `WartegCanggih.java`: method `main`, menyiapkan menu lalu membuka kasir.
- `Menu.java`: nama dan harga menu.
- `ItemPesanan.java`: satu item pesanan di dalam stack.
- `Kasir.java`: antrian, pesanan, struk, pembayaran, dan rekap harian.

Input contoh di bawah sengaja berisi ketikan yang salah ("dua", "lima ribu", nama kosong) dan berhenti di tengah transaksi, supaya penanganan exception-nya kelihatan.

## Kebutuhan

- JDK 21 (misalnya [Eclipse Temurin 21](https://adoptium.net/temurin/releases))

## Cara Menjalankan

Compile semua file dulu, lalu jalankan class yang punya `main`:

```
javac *.java
java WartegCanggih
```

## Contoh Input

Program ini interaktif, jadi jalankan di terminal yang bisa diketik. Untuk mencoba cepat tanpa mengetik satu per satu, input contoh bisa dikirim lewat pipe (setelah `javac *.java`):

```
printf '1
Budi
dua
1

1
Siti
2
2
2
1
1
-1
0
lima ribu
20000
2
3
' | java WartegCanggih
```

## Output (dengan input contoh di atas)

```
============================
  KASIR WARTEG CANGGIH
============================

1. Tambah antrian
2. Layani antrian berikutnya
3. Lihat antrian
4. Lihat daftar menu
0. Tutup kasir
Pilih: Atas nama siapa? Budi masuk antrian ke-1.

1. Tambah antrian
2. Layani antrian berikutnya
3. Lihat antrian
4. Lihat daftar menu
0. Tutup kasir
Pilih: "dua" bukan angka, coba lagi: Atas nama siapa? Pembeli masuk antrian ke-2.

1. Tambah antrian
2. Layani antrian berikutnya
3. Lihat antrian
4. Lihat daftar menu
0. Tutup kasir
Pilih: Atas nama siapa? Siti masuk antrian ke-3.

1. Tambah antrian
2. Layani antrian berikutnya
3. Lihat antrian
4. Lihat daftar menu
0. Tutup kasir
Pilih: Melayani Budi.
Daftar menu:
1. Nasi putih (Rp5000)
2. Tempe orek (Rp4000)
3. Es teh (Rp3000)
4. Telur balado (Rp6000)
5. Sayur asem (Rp4000)
Nomor menu (0 = selesai, -1 = batalkan terakhir): Berapa porsi? Dicatat: Tempe orek x2
Nomor menu (0 = selesai, -1 = batalkan terakhir): Berapa porsi? Dicatat: Nasi putih x1
Nomor menu (0 = selesai, -1 = batalkan terakhir): Dibatalkan: Nasi putih x1
Nomor menu (0 = selesai, -1 = batalkan terakhir): 
Pesanan atas nama Budi
Tempe orek     x2     Rp8000
----------------------------
Subtotal              Rp8000
Diskon                   Rp0
TOTAL                 Rp8000
Uang pembeli: "lima ribu" bukan angka, coba lagi: Kembalian: Rp12000
  Rp10000
  Rp2000

1. Tambah antrian
2. Layani antrian berikutnya
3. Lihat antrian
4. Lihat daftar menu
0. Tutup kasir
Pilih: Melayani Pembeli.
Daftar menu:
1. Nasi putih (Rp5000)
2. Tempe orek (Rp4000)
3. Es teh (Rp3000)
4. Telur balado (Rp6000)
5. Sayur asem (Rp4000)
Nomor menu (0 = selesai, -1 = batalkan terakhir): Berapa porsi? 
Input terhenti, kasir ditutup paksa.

REKAP HARI INI
----------------------------
Nasi putih           0 porsi
Tempe orek           2 porsi
Es teh               0 porsi
Telur balado         0 porsi
Sayur asem           0 porsi
----------------------------
Pembeli              1 orang
Omzet                 Rp8000
Menu terlaris: Tempe orek
```
