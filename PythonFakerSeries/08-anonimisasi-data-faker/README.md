# 8. Anonimisasi Data Asli secara Konsisten

Bagian dari **Library Faker Python Series** di [ideacak.com](https://www.ideacak.com/search/label/Faker%20Python).

Nama, email, dan telepon di data pelanggan disamarkan dengan Faker tanpa memutus relasi ke tabel transaksi. Seed Faker diturunkan dari HMAC-SHA256 nilai asli dengan kunci rahasia, jadi nilai asli yang sama selalu mendapat samaran yang sama. Secara teknis ini pseudonimisasi, jadi hasilnya tetap diperlakukan sebagai data pribadi.

| File | Isi |
|---|---|
| `1_buat_data_contoh.py` | Membuat `pelanggan.csv` dan `transaksi.csv` sebagai "data asli" contoh (isinya tetap buatan Faker) |
| `2_ganti_acak.py` | Cara naif: setiap sel diganti nilai acak, relasi putus |
| `3_kamus_pengganti.py` | Kamus pengganti: relasi utuh, tapi hasil berubah setiap kali dijalankan |
| `4_seed_dari_hash.py` | `hash()` yang berubah per proses, penyamar HMAC yang konsisten, dan akibat email tanpa akhiran sidik |
| `5_samarkan_csv.py` | Menyamarkan kedua file ke `*_samaran.csv` lalu memeriksa hasilnya |
| `6_tantangan.py` | Jawaban tiga tantangan di akhir artikel |
| `penyamar.py` | Fungsi `samarkan_nama()`, `samarkan_email()`, `samarkan_telepon()` (diimpor oleh skrip lain) |
| `data_csv.py` | Baca dan tulis CSV (diimpor oleh skrip lain) |

## Kunci Rahasia

`penyamar.py` membaca kunci dari environment variable `KUNCI_SAMARAN`. Kalau tidak diisi, dipakai kunci contoh supaya skrip bisa langsung dicoba. Untuk data sungguhan, selalu isi kunci sendiri dan simpan terpisah dari data samaran:

```
# Windows PowerShell
$env:KUNCI_SAMARAN = "isi-dengan-kunci-rahasiamu"

# Linux / macOS
export KUNCI_SAMARAN="isi-dengan-kunci-rahasiamu"
```

## Kebutuhan

- Python 3.10 ke atas (diuji dengan Python 3.14)
- Faker 40 ke atas (diuji dengan Faker 40.43.0)

```
pip install -r requirements.txt
```

## Cara Menjalankan

Jalankan berurutan, karena skrip 2 sampai 6 membaca CSV dari skrip 1 dan 5:

```
python 1_buat_data_contoh.py
python 2_ganti_acak.py
python 3_kamus_pengganti.py
python 4_seed_dari_hash.py
python 5_samarkan_csv.py
python 6_tantangan.py
```

Angka `hash()` di `4_seed_dari_hash.py` memang berbeda setiap kali dijalankan. Itulah yang ingin ditunjukkan.

## Output `5_samarkan_csv.py`

```
{'id': '1', 'nama': 'Tgk. Prabowo Prakasa', 'email': 'waskitairwan.d61d5c@example.com', 'telepon': '086296319402', 'kota': 'Surabaya'}
Transaksi tersambung: 5000 dari 5000
Email samaran berbeda: 1000 dari 1000
Email asli yang tersisa: 0
Total nominal sama: True
```

Teknik di sini bukan nasihat hukum. Untuk membagikan data pribadi sungguhan, ikuti kebijakan perlindungan data di organisasimu.
