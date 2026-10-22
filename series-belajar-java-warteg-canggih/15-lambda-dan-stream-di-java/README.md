# 15. Lambda dan Stream di Java

Bagian dari **Seri Belajar Pemrograman Java Studi Kasus Warteg Canggih** di [ideacak.com](https://www.ideacak.com/search/label/Warteg%20Canggih%20Series).

Kasir versi 14 memakai lambda dan stream untuk bagian yang mengolah data:

- `AturanDiskon` diberi `@FunctionalInterface`, dan `DiskonJamSepi.java` dihapus karena cukup ditulis sebagai lambda `subtotal -> subtotal / 10` di `main`.
- Ringkasan pesanan dan rekap harian memakai `forEach` dan `Map.merge`.
- `hitungSubtotal` memakai `stream().mapToInt(...).sum()`.
- Rekap harian menampilkan total porsi, tiga menu terlaris, dan menu yang belum laku, masing-masing dari satu alur stream.

Menu, antrian, pembatalan pesanan, dan struk tidak berubah dari versi 14.

File:

- `WartegCanggih.java`: method `main`, menyiapkan menu, memilih aturan diskon, lalu membuka kasir.
- `Menu.java`, `MenuPaket.java`: menu biasa dan menu paket.
- `AturanDiskon.java`, `DiskonBertingkat.java`: aturan diskon.
- `ItemPesanan.java`: satu item pesanan (menu dan jumlah porsi).
- `Kasir.java`: antrian, menu, pesanan, struk, pembayaran, dan rekap harian.

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
1
Siti
2
2
2
1
1
3
1
-1
2
0
20000
5
Perkedel
3000
2
7
2
3
1
2
1
0
20000
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
5. Tambah menu
0. Tutup kasir
Pilih: Atas nama siapa? Budi masuk antrian ke-1.

1. Tambah antrian
2. Layani antrian berikutnya
3. Lihat antrian
4. Lihat daftar menu
5. Tambah menu
0. Tutup kasir
Pilih: Atas nama siapa? Siti masuk antrian ke-2.

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
Nomor menu (0 = selesai, -1 = batalkan pesanan): Berapa porsi? Dicatat: Es teh x1
Nomor menu (0 = selesai, -1 = batalkan pesanan): Pesanan sejauh ini:
  1. Tempe orek x2
  2. Nasi putih x1
  3. Es teh x1
Batalkan nomor berapa? Dibatalkan: Nasi putih x1
Nomor menu (0 = selesai, -1 = batalkan pesanan): 
Pesanan atas nama Budi
Tempe orek     x2     Rp8000
Es teh         x1     Rp3000
----------------------------
Subtotal             Rp11000
Diskon                Rp1000
TOTAL                Rp10000
Uang pembeli: Kembalian: Rp10000
  Rp10000

1. Tambah antrian
2. Layani antrian berikutnya
3. Lihat antrian
4. Lihat daftar menu
5. Tambah menu
0. Tutup kasir
Pilih: Nama menu baru: Harga: Perkedel ditambahkan sebagai menu nomor 7.

1. Tambah antrian
2. Layani antrian berikutnya
3. Lihat antrian
4. Lihat daftar menu
5. Tambah menu
0. Tutup kasir
Pilih: Melayani Siti.
Daftar menu:
1. Nasi putih (Rp5000)
2. Tempe orek (Rp4000)
3. Es teh (Rp3000)
4. Telur balado (Rp6000)
5. Sayur asem (Rp4000)
6. Paket Hemat (Rp10000)
   isi: Nasi putih, Tempe orek, Es teh
7. Perkedel (Rp3000)
Nomor menu (0 = selesai, -1 = batalkan pesanan): Berapa porsi? Dicatat: Perkedel x2
Nomor menu (0 = selesai, -1 = batalkan pesanan): Berapa porsi? Dicatat: Es teh x1
Nomor menu (0 = selesai, -1 = batalkan pesanan): Berapa porsi? Dicatat: Tempe orek x1
Nomor menu (0 = selesai, -1 = batalkan pesanan): 
Pesanan atas nama Siti
Perkedel       x2     Rp6000
Es teh         x1     Rp3000
Tempe orek     x1     Rp4000
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
5. Tambah menu
0. Tutup kasir
Pilih: Kasir ditutup.

REKAP HARI INI
----------------------------
Nasi putih           0 porsi
Tempe orek           3 porsi
Es teh               2 porsi
Telur balado         0 porsi
Sayur asem           0 porsi
Paket Hemat          0 porsi
Perkedel             2 porsi
----------------------------
Total porsi          7 porsi
Pembeli              2 orang
Omzet                Rp22000
Terlaris: Tempe orek (3), Es teh (2), Perkedel (2)
Belum laku: Nasi putih, Telur balado, Sayur asem, Paket Hemat
```
