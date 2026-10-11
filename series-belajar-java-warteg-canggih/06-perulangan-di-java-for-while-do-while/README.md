# 06. Perulangan di Java: for, while, do-while

Bagian dari **Seri Belajar Pemrograman Java Studi Kasus Warteg Canggih** di [ideacak.com](https://www.ideacak.com/search/label/Warteg%20Canggih%20Series).

Di bagian ini, kasir bisa memproses banyak pesanan satu meja sekaligus, melewati nomor menu yang tidak ada, dan memecah kembalian jadi lembaran uang.

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

## Output

```
Selamat datang di Warteg Canggih!
Daftar menu:
1. Nasi putih (Rp5000)
2. Tempe orek (Rp4000)
3. Es teh (Rp3000)

Nasi putih        Rp5000
Tempe orek        Rp4000
Tempe orek        Rp4000
Menu nomor 5 tidak ada, dilewati
Es teh            Rp3000
------------------------
Item                   4
Subtotal         Rp16000
Diskon            Rp1000
TOTAL            Rp15000
Kembalian: Rp35000
  Rp10000
  Rp10000
  Rp10000
  Rp5000
```
