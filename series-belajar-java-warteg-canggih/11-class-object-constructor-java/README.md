# 11. Class, Object, dan Constructor di Java

Bagian dari **Seri Belajar Pemrograman Java Studi Kasus Warteg Canggih** di [ideacak.com](https://www.ideacak.com/search/label/Warteg%20Canggih%20Series).

Pembuka Tahap 2. Array paralel `namaMenu` dan `hargaMenu` diganti class `Menu`, dan data harian (omzet, jumlah pembeli, rekap porsi) pindah ke class `Kasir`. Hasil programnya sama dengan versi 10, tapi sebagian besar `static` sudah hilang.

Isi file `WartegCanggih.java`:

- `WartegCanggih`: method `main`, menyiapkan `Menu[]` lalu membuka kasir.
- `Menu`: field `nama` dan `harga`, constructor, `hitungHarga()`, dan `tampilkan()`.
- `Kasir`: field data harian, constructor, dan semua method untuk melayani pembeli.

## Kebutuhan

- JDK 21 (misalnya [Eclipse Temurin 21](https://adoptium.net/temurin/releases))

## Cara Menjalankan

Cara klasik, compile dulu lalu jalankan:

```
javac WartegCanggih.java
java WartegCanggih
```

Jalan pintas (JDK 11 ke atas), tanpa compile manual:

```
java WartegCanggih.java
```

## Contoh Input

Program ini interaktif, jadi jalankan di terminal yang bisa diketik. Untuk mencoba cepat tanpa mengetik satu per satu, input contoh bisa dikirim lewat pipe:

```
printf '1
Budi
2
2
1
1
0
20000
2
1
Siti
3
2
4
1
0
15000
0
' | java WartegCanggih.java
```

## Output (dengan input contoh di atas)

```
============================
  KASIR WARTEG CANGGIH
============================

1. Layani pembeli
2. Lihat daftar menu
0. Tutup kasir
Pilih: Atas nama siapa? Daftar menu:
1. Nasi putih (Rp5000)
2. Tempe orek (Rp4000)
3. Es teh (Rp3000)
4. Telur balado (Rp6000)
5. Sayur asem (Rp4000)
Nomor menu (0 = selesai): Berapa porsi? Dicatat: Tempe orek x2
Nomor menu (0 = selesai): Berapa porsi? Dicatat: Nasi putih x1
Nomor menu (0 = selesai): 
Pesanan atas nama Budi
Nasi putih     x1     Rp5000
Tempe orek     x2     Rp8000
----------------------------
Subtotal             Rp13000
Diskon                Rp1000
TOTAL                Rp12000
Uang pembeli: Kembalian: Rp8000
  Rp5000
  Rp2000
  Rp1000

1. Layani pembeli
2. Lihat daftar menu
0. Tutup kasir
Pilih: Daftar menu:
1. Nasi putih (Rp5000)
2. Tempe orek (Rp4000)
3. Es teh (Rp3000)
4. Telur balado (Rp6000)
5. Sayur asem (Rp4000)

1. Layani pembeli
2. Lihat daftar menu
0. Tutup kasir
Pilih: Atas nama siapa? Daftar menu:
1. Nasi putih (Rp5000)
2. Tempe orek (Rp4000)
3. Es teh (Rp3000)
4. Telur balado (Rp6000)
5. Sayur asem (Rp4000)
Nomor menu (0 = selesai): Berapa porsi? Dicatat: Es teh x2
Nomor menu (0 = selesai): Berapa porsi? Dicatat: Telur balado x1
Nomor menu (0 = selesai): 
Pesanan atas nama Siti
Es teh         x2     Rp6000
Telur balado   x1     Rp6000
----------------------------
Subtotal             Rp12000
Diskon                Rp1000
TOTAL                Rp11000
Uang pembeli: Kembalian: Rp4000
  Rp2000
  Rp2000

1. Layani pembeli
2. Lihat daftar menu
0. Tutup kasir
Pilih: Kasir ditutup.

REKAP HARI INI
----------------------------
Nasi putih           1 porsi
Tempe orek           2 porsi
Es teh               2 porsi
Telur balado         1 porsi
Sayur asem           0 porsi
----------------------------
Pembeli              2 orang
Omzet                Rp23000
Menu terlaris: Tempe orek
```
