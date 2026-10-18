# 15. Membaca dan Menulis File di Java

Bagian dari **Seri Belajar Pemrograman Java Studi Kasus Warteg Canggih** di [ideacak.com](https://www.ideacak.com/search/label/Warteg%20Canggih%20Series).

Daftar menu dan stok dibaca dari `data/menu.txt`, bukan ditulis di kode lagi. Setiap struk ditambahkan ke file harian `data/struk-<tanggal>.txt`, dan saat kasir tutup, sisa stok ditulis balik ke `menu.txt` serta rekap hari itu ditambahkan ke `data/rekap.txt`. Semua urusan file ada di class `ArsipWarteg`.

- `WartegCanggih.java`: method `main`, membaca menu dari file lalu membuka kasir.
- `ArsipWarteg.java`: membaca dan menulis semua file di folder `data`.
- `Kasir.java`: antrian, pesanan, struk, pembayaran, rekap, dan penyimpanan akhir hari.
- `Menu.java`, `ItemPesanan.java`, `PesananTidakValidException.java`, `StokTidakCukupException.java`: sama seperti versi 14.
- `data/menu.txt`: daftar menu awal, format `nama;harga;stok`.

**Catatan:** program ini mengubah `data/menu.txt` (stok berkurang) dan membuat `rekap.txt` serta `struk-<tanggal>.txt` di folder `data`. Kalau ingin mengembalikan stok ke awal setelah mencoba, jalankan `git checkout data/menu.txt` dan hapus file struk serta rekap-nya.

Program harus dijalankan dari folder ini (folder yang berisi folder `data`), karena path `data/menu.txt` dihitung dari folder tempat perintah `java` diketik.

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
2
2
4
2
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
Nomor menu (0 = selesai, -1 = batalkan terakhir): Berapa porsi? Dicatat: Tempe orek x2
Nomor menu (0 = selesai, -1 = batalkan terakhir): Berapa porsi? Dicatat: Telur balado x2
Nomor menu (0 = selesai, -1 = batalkan terakhir): 
Pesanan atas nama Budi
Tempe orek     x2     Rp8000
Telur balado   x2    Rp12000
----------------------------
Subtotal             Rp20000
Diskon                Rp2000
TOTAL                Rp18000
Uang pembeli: Kembalian: Rp2000
  Rp2000

1. Tambah antrian
2. Layani antrian berikutnya
3. Lihat antrian
4. Lihat daftar menu
0. Tutup kasir
Pilih: Kasir ditutup.

REKAP HARI INI
----------------------------
Nasi putih           0 porsi
Tempe orek           2 porsi
Es teh               0 porsi
Telur balado         2 porsi
Sayur asem           0 porsi
----------------------------
Pembeli              1 orang
Omzet                Rp18000
Menu terlaris: Tempe orek
Sisa stok dan rekap hari ini tersimpan di folder data.
```
