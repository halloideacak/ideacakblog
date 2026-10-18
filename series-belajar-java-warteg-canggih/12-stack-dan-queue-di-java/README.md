# 12. Stack dan Queue di Java: Konsep LIFO dan FIFO

Bagian dari **Seri Belajar Pemrograman Java Studi Kasus Warteg Canggih** di [ideacak.com](https://www.ideacak.com/search/label/Warteg%20Canggih%20Series).

Antrian pembeli disimpan sebagai queue (FIFO), dan pesanan setiap pembeli disimpan sebagai stack (LIFO) supaya pesanan terakhir bisa dibatalkan. Keduanya memakai `ArrayDeque`. Mulai versi ini, setiap class punya file sendiri.

- `WartegCanggih.java`: method `main`, menyiapkan menu lalu membuka kasir.
- `Menu.java`: nama dan harga menu.
- `ItemPesanan.java`: satu item pesanan (index menu dan jumlah porsi) di dalam stack.
- `Kasir.java`: antrian, pesanan, struk, pembayaran, dan rekap harian.

## Kebutuhan

- JDK 21 (misalnya [Eclipse Temurin 21](https://adoptium.net/temurin/releases))

## Cara Menjalankan

Karena programnya terdiri dari beberapa file, compile semuanya dulu, lalu jalankan class yang punya `main`:

```
javac *.java
java WartegCanggih
```

Di JDK 22 ke atas, `java WartegCanggih.java` juga bisa dipakai untuk program multi-file ([JEP 458](https://openjdk.org/jeps/458)).

## Contoh Input

Program ini interaktif, jadi jalankan di terminal yang bisa diketik. Untuk mencoba cepat tanpa mengetik satu per satu, input contoh bisa dikirim lewat pipe (setelah `javac *.java`):

```
printf '1
Budi
1
Siti
0
3
2
2
2
1
1
3
1
-1
0
20000
2
-1
3
2
4
1
0
15000
2
0
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
Pilih: Atas nama siapa? Siti masuk antrian ke-2.

1. Tambah antrian
2. Layani antrian berikutnya
3. Lihat antrian
4. Lihat daftar menu
0. Tutup kasir
Pilih: Masih ada 2 pembeli mengantre. Layani dulu.

1. Tambah antrian
2. Layani antrian berikutnya
3. Lihat antrian
4. Lihat daftar menu
0. Tutup kasir
Pilih: Antrian saat ini:
1. Budi
2. Siti

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
Nomor menu (0 = selesai, -1 = batalkan terakhir): Berapa porsi? Dicatat: Es teh x1
Nomor menu (0 = selesai, -1 = batalkan terakhir): Dibatalkan: Es teh x1
Nomor menu (0 = selesai, -1 = batalkan terakhir): 
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

1. Tambah antrian
2. Layani antrian berikutnya
3. Lihat antrian
4. Lihat daftar menu
0. Tutup kasir
Pilih: Melayani Siti.
Daftar menu:
1. Nasi putih (Rp5000)
2. Tempe orek (Rp4000)
3. Es teh (Rp3000)
4. Telur balado (Rp6000)
5. Sayur asem (Rp4000)
Nomor menu (0 = selesai, -1 = batalkan terakhir): Belum ada pesanan yang bisa dibatalkan.
Nomor menu (0 = selesai, -1 = batalkan terakhir): Berapa porsi? Dicatat: Es teh x2
Nomor menu (0 = selesai, -1 = batalkan terakhir): Berapa porsi? Dicatat: Telur balado x1
Nomor menu (0 = selesai, -1 = batalkan terakhir): 
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

1. Tambah antrian
2. Layani antrian berikutnya
3. Lihat antrian
4. Lihat daftar menu
0. Tutup kasir
Pilih: Antrian kosong, belum ada yang bisa dilayani.

1. Tambah antrian
2. Layani antrian berikutnya
3. Lihat antrian
4. Lihat daftar menu
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
