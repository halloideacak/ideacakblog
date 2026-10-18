# 14. Custom Exception untuk Validasi Bisnis

Bagian dari **Seri Belajar Pemrograman Java Studi Kasus Warteg Canggih** di [ideacak.com](https://www.ideacak.com/search/label/Warteg%20Canggih%20Series).

Aturan bisnis warteg dijaga dengan exception buatan sendiri. Field `Menu` dibuat `private` dengan getter, setiap menu punya stok, dan stok hanya bisa dikurangi lewat `kurangiStok()` yang melempar `StokTidakCukupException`. Aturan porsi 1 sampai 20 melempar `PesananTidakValidException`. Pesanan yang dibatalkan mengembalikan stoknya.

- `WartegCanggih.java`: method `main`, menyiapkan menu beserta stoknya lalu membuka kasir.
- `Menu.java`: nama, harga, dan stok menu (private, dengan getter).
- `ItemPesanan.java`: satu item pesanan di dalam stack.
- `Kasir.java`: antrian, pesanan, validasi, struk, pembayaran, dan rekap harian.
- `PesananTidakValidException.java`: induk semua pelanggaran aturan pesanan (checked).
- `StokTidakCukupException.java`: turunannya, membawa data sisa stok.

Input contoh di bawah sengaja memesan telur balado melebihi stok, sayur asem yang habis, dan 25 porsi tempe, supaya ketiga aturan terlihat.

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
2
4
5
4
2
5
1
2
25
2
2
-1
0
20000
4
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
Pilih: Melayani Budi.
Daftar menu:
1. Nasi putih (Rp5000, sisa 50)
2. Tempe orek (Rp4000, sisa 20)
3. Es teh (Rp3000, sisa 40)
4. Telur balado (Rp6000, sisa 3)
5. Sayur asem (Rp4000, habis)
Nomor menu (0 = selesai, -1 = batalkan terakhir): Berapa porsi? Ditolak: Stok Telur balado tinggal 3, tidak cukup untuk 5 porsi.
Tawarkan 3 porsi saja, atau menu lain.
Nomor menu (0 = selesai, -1 = batalkan terakhir): Berapa porsi? Dicatat: Telur balado x2
Nomor menu (0 = selesai, -1 = batalkan terakhir): Berapa porsi? Ditolak: Stok Sayur asem tinggal 0, tidak cukup untuk 1 porsi.
Nomor menu (0 = selesai, -1 = batalkan terakhir): Berapa porsi? Ditolak: Jumlah porsi harus 1 sampai 20.
Nomor menu (0 = selesai, -1 = batalkan terakhir): Berapa porsi? Dicatat: Tempe orek x2
Nomor menu (0 = selesai, -1 = batalkan terakhir): Dibatalkan: Tempe orek x2
Nomor menu (0 = selesai, -1 = batalkan terakhir): 
Pesanan atas nama Budi
Telur balado   x2    Rp12000
----------------------------
Subtotal             Rp12000
Diskon                Rp1000
TOTAL                Rp11000
Uang pembeli: Kembalian: Rp9000
  Rp5000
  Rp2000
  Rp2000

1. Tambah antrian
2. Layani antrian berikutnya
3. Lihat antrian
4. Lihat daftar menu
0. Tutup kasir
Pilih: Daftar menu:
1. Nasi putih (Rp5000, sisa 50)
2. Tempe orek (Rp4000, sisa 20)
3. Es teh (Rp3000, sisa 40)
4. Telur balado (Rp6000, sisa 1)
5. Sayur asem (Rp4000, habis)

1. Tambah antrian
2. Layani antrian berikutnya
3. Lihat antrian
4. Lihat daftar menu
0. Tutup kasir
Pilih: Kasir ditutup.

REKAP HARI INI
----------------------------
Nasi putih           0 porsi
Tempe orek           0 porsi
Es teh               0 porsi
Telur balado         2 porsi
Sayur asem           0 porsi
----------------------------
Pembeli              1 orang
Omzet                Rp11000
Menu terlaris: Telur balado
```
