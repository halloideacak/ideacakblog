# 09. Membaca Input dari Pengguna dengan Scanner

Bagian dari **Seri Belajar Pemrograman Java Studi Kasus Warteg Canggih** di [ideacak.com](https://www.ideacak.com/search/label/Warteg%20Canggih%20Series).

Di bagian ini, kasir menerima nama pembeli, pesanan, catatan, dan uang pembeli yang diketik langsung lewat keyboard.

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
printf 'Budi
2
2
7
1
dua
1
4
1
0
sambalnya dipisah
15000
30000
' | java WartegCanggih.java
```

## Output (dengan input contoh di atas)

```
Selamat datang di Warteg Canggih!
Atas nama siapa? Daftar menu:
1. Nasi putih (Rp5000)
2. Tempe orek (Rp4000)
3. Es teh (Rp3000)
4. Telur balado (Rp6000)
5. Sayur asem (Rp4000)
Nomor menu (0 = selesai): Berapa porsi? Dicatat: Tempe orek x2
Nomor menu (0 = selesai): Menu nomor 7 tidak ada.
Nomor menu (0 = selesai): Berapa porsi? "dua" bukan angka, coba lagi: Dicatat: Nasi putih x1
Nomor menu (0 = selesai): Berapa porsi? Dicatat: Telur balado x1
Nomor menu (0 = selesai): Catatan untuk dapur (boleh kosong): 
Pesanan atas nama Budi
Nasi putih     x1     Rp5000
Tempe orek     x2     Rp8000
Telur balado   x1     Rp6000
----------------------------
Subtotal             Rp19000
Diskon                Rp1000
TOTAL                Rp18000
Catatan: sambalnya dipisah

Uang pembeli: Uang kurang Rp3000. Masukkan ulang: Kembalian: Rp12000
  Rp10000
  Rp2000
```
