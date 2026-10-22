# 16. Exception Handling di Java: try, catch, finally

Bagian dari **Seri Belajar Pemrograman Java Studi Kasus Warteg Canggih** di [ideacak.com](https://www.ideacak.com/search/label/Warteg%20Canggih%20Series).

Kasir versi 15 membaca input per baris dan memakai `try-catch` untuk menangkap `NumberFormatException`, jadi trik "buang sisa Enter" di `tambahAntrian()` dan `tambahMenu()` tidak dibutuhkan lagi. Menu utama dibungkus `try-catch-finally` supaya rekap harian tetap dicetak walaupun input terhenti mendadak, dan Scanner ditutup dengan try-with-resources.

File:

- `WartegCanggih.java`: method `main`, menyiapkan menu, memilih aturan diskon, lalu membuka kasir (Scanner dengan try-with-resources).
- `Menu.java`, `MenuPaket.java`: menu biasa dan menu paket.
- `AturanDiskon.java`, `DiskonBertingkat.java`: aturan diskon.
- `ItemPesanan.java`: satu item pesanan (menu dan jumlah porsi).
- `Kasir.java`: antrian, menu, pesanan, struk, pembayaran, dan rekap harian.

Input contoh di bawah sengaja berisi ketikan yang salah ("dua", "lima ribu", nama kosong) dan berhenti di tengah transaksi, supaya penanganan exception-nya kelihatan.

## Kebutuhan

- JDK 21 (misalnya [Eclipse Temurin 21](https://adoptium.net/temurin/releases))

## Cara Menjalankan

```
javac *.java
java WartegCanggih
```

## Contoh Input

Input contoh bisa dikirim lewat pipe (setelah `javac *.java`):

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
2
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
5. Tambah menu
0. Tutup kasir
Pilih: Atas nama siapa? Budi masuk antrian ke-1.

1. Tambah antrian
2. Layani antrian berikutnya
3. Lihat antrian
4. Lihat daftar menu
5. Tambah menu
0. Tutup kasir
Pilih: "dua" bukan angka, coba lagi: Atas nama siapa? Pembeli masuk antrian ke-2.

1. Tambah antrian
2. Layani antrian berikutnya
3. Lihat antrian
4. Lihat daftar menu
5. Tambah menu
0. Tutup kasir
Pilih: Atas nama siapa? Siti masuk antrian ke-3.

1. Tambah antrian
2. Layani antrian berikutnya
3. Lihat antrian
4. Lihat daftar menu
5. Tambah menu
0. Tutup kasir
Pilih: Melayani Budi.
Daftar menu:
1. Nasi putih (Rp5000)
2. Tempe orek (Rp4000)
3. Es teh (Rp3000)
4. Telur balado (Rp6000)
5. Sayur asem (Rp4000)
6. Paket Hemat (Rp10000)
   isi: Nasi putih, Tempe orek, Es teh
Nomor menu (0 = selesai, -1 = batalkan pesanan): Berapa porsi? Dicatat: Tempe orek x2
Nomor menu (0 = selesai, -1 = batalkan pesanan): Berapa porsi? Dicatat: Nasi putih x1
Nomor menu (0 = selesai, -1 = batalkan pesanan): Pesanan sejauh ini:
  1. Tempe orek x2
  2. Nasi putih x1
Batalkan nomor berapa? Dibatalkan: Nasi putih x1
Nomor menu (0 = selesai, -1 = batalkan pesanan): 
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
5. Tambah menu
0. Tutup kasir
Pilih: Melayani Pembeli.
Daftar menu:
1. Nasi putih (Rp5000)
2. Tempe orek (Rp4000)
3. Es teh (Rp3000)
4. Telur balado (Rp6000)
5. Sayur asem (Rp4000)
6. Paket Hemat (Rp10000)
   isi: Nasi putih, Tempe orek, Es teh
Nomor menu (0 = selesai, -1 = batalkan pesanan): Berapa porsi? 
Input terhenti, kasir ditutup paksa.

REKAP HARI INI
----------------------------
Nasi putih           0 porsi
Tempe orek           2 porsi
Es teh               0 porsi
Telur balado         0 porsi
Sayur asem           0 porsi
Paket Hemat          0 porsi
----------------------------
Total porsi          2 porsi
Pembeli              1 orang
Omzet                 Rp8000
Terlaris: Tempe orek (2)
Belum laku: Nasi putih, Es teh, Telur balado, Sayur asem, Paket Hemat
```
