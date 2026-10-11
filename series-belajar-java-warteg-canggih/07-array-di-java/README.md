# 07. Array di Java

Bagian dari **Seri Belajar Pemrograman Java Studi Kasus Warteg Canggih** di [ideacak.com](https://www.ideacak.com/search/label/Warteg%20Canggih%20Series).

Di bagian ini, daftar menu cukup ditulis sekali di array, pesanan satu meja disimpan sebagai `int[]`, dan struk menampilkan jumlah porsi per menu.

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
4. Telur balado (Rp6000)
5. Sayur asem (Rp4000)

Pesanan meja: [1, 2, 2, 4, 9, 3]
Menu nomor 9 tidak ada, dilewati

Nasi putih     x1     Rp5000
Tempe orek     x2     Rp8000
Es teh         x1     Rp3000
Telur balado   x1     Rp6000
----------------------------
Subtotal             Rp22000
Diskon                Rp2000
TOTAL                Rp20000
Kembalian: Rp18000
  Rp10000
  Rp5000
  Rp2000
  Rp1000
```
