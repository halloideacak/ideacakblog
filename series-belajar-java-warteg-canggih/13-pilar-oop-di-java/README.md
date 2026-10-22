# 13. Pilar OOP di Java: Encapsulation, Inheritance, Polymorphism, dan Abstraction

Bagian dari **Seri Belajar Pemrograman Java Studi Kasus Warteg Canggih** di [ideacak.com](https://www.ideacak.com/search/label/Warteg%20Canggih%20Series).

Kasir versi 12 ditata ulang memakai empat pilar OOP:

- **Encapsulation**: semua field `Menu`, `ItemPesanan`, dan `Kasir` dibuat `private`. Harga hanya bisa diubah lewat `ubahHarga`, yang menolak harga 0 atau minus.
- **Inheritance**: `MenuPaket extends Menu`, sehingga Paket Hemat mewarisi nama, harga, dan perhitungan harga dari `Menu`.
- **Polymorphism**: `MenuPaket` meng-override `tampilkan()` untuk menampilkan isi paket. Loop daftar menu di `Kasir` tidak berubah.
- **Abstraction**: aturan diskon jadi `interface AturanDiskon`, dengan dua implementasi `DiskonBertingkat` dan `DiskonJamSepi`. `Kasir` menerimanya lewat constructor.

File:

- `WartegCanggih.java`: method `main`, menyiapkan menu, memilih aturan diskon, lalu membuka kasir.
- `Menu.java`: nama dan harga menu (field `private`, getter, `ubahHarga`).
- `MenuPaket.java`: menu paket beserta isinya.
- `AturanDiskon.java`, `DiskonBertingkat.java`, `DiskonJamSepi.java`: aturan diskon.
- `ItemPesanan.java`: satu item pesanan (index menu dan jumlah porsi) di dalam stack.
- `Kasir.java`: antrian, pesanan, struk, pembayaran, dan rekap harian.

## Kebutuhan

- JDK 21 (misalnya [Eclipse Temurin 21](https://adoptium.net/temurin/releases))

## Cara Menjalankan

```
javac *.java
java WartegCanggih
```

Untuk mencoba aturan diskon jam sepi, ganti `new DiskonBertingkat()` jadi `new DiskonJamSepi()` di `WartegCanggih.java`.

## Contoh Input

Input contoh bisa dikirim lewat pipe (setelah `javac *.java`):

```
printf '1
Budi
1
Siti
4
2
6
1
4
1
0
20000
2
3
2
2
1
-1
0
10000
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
Pilih: Daftar menu:
1. Nasi putih (Rp5000)
2. Tempe orek (Rp4000)
3. Es teh (Rp3000)
4. Telur balado (Rp6000)
5. Sayur asem (Rp4000)
6. Paket Hemat (Rp10000)
   isi: Nasi putih, Tempe orek, Es teh

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
6. Paket Hemat (Rp10000)
   isi: Nasi putih, Tempe orek, Es teh
Nomor menu (0 = selesai, -1 = batalkan terakhir): Berapa porsi? Dicatat: Paket Hemat x1
Nomor menu (0 = selesai, -1 = batalkan terakhir): Berapa porsi? Dicatat: Telur balado x1
Nomor menu (0 = selesai, -1 = batalkan terakhir): 
Pesanan atas nama Budi
Telur balado   x1     Rp6000
Paket Hemat    x1    Rp10000
----------------------------
Subtotal             Rp16000
Diskon                Rp1000
TOTAL                Rp15000
Uang pembeli: Kembalian: Rp5000
  Rp5000

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
6. Paket Hemat (Rp10000)
   isi: Nasi putih, Tempe orek, Es teh
Nomor menu (0 = selesai, -1 = batalkan terakhir): Berapa porsi? Dicatat: Es teh x2
Nomor menu (0 = selesai, -1 = batalkan terakhir): Berapa porsi? Dicatat: Tempe orek x1
Nomor menu (0 = selesai, -1 = batalkan terakhir): Dibatalkan: Tempe orek x1
Nomor menu (0 = selesai, -1 = batalkan terakhir): 
Pesanan atas nama Siti
Es teh         x2     Rp6000
----------------------------
Subtotal              Rp6000
Diskon                   Rp0
TOTAL                 Rp6000
Uang pembeli: Kembalian: Rp4000
  Rp2000
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
Tempe orek           0 porsi
Es teh               2 porsi
Telur balado         1 porsi
Sayur asem           0 porsi
Paket Hemat          1 porsi
----------------------------
Pembeli              2 orang
Omzet                Rp21000
Menu terlaris: Es teh
```
