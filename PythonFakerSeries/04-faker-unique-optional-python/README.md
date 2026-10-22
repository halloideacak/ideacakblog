# 4. Data Dummy Unik, Opsional, dan Berbobot dengan Faker Python

Bagian dari **Library Faker Python Series** di [ideacak.com](https://www.ideacak.com/search/label/Faker%20Python).

Data dummy dari Faker sering terlalu acak (ada email kembar), terlalu lengkap (tidak ada kolom kosong), dan terlalu rata (semua pilihan muncul sama sering). Artikel ini memperbaikinya dengan `fake.unique`, `fake.optional`, `random_element` berbobot, dan mode multi-locale.

| File | Isi |
|---|---|
| `email_kembar.py` | Pembuka artikel: 1.000 pelanggan dummy gagal masuk ke tabel SQLite yang email-nya `UNIQUE` |
| `hitung_kembar.py` | Menghitung nama depan dan email yang kembar |
| `unik_manual.py` | Menjaga email tetap unik dengan `set` dan `while` |
| `unik_faker.py` | `fake.unique`, `UniquenessException`, dan `fake.unique.clear()` |
| `opsional.py` | `fake.optional` dengan `prob`, lalu disimpan ke CSV dan JSON |
| `berbobot.py` | `random_element` dan `random_elements` dengan `OrderedDict` berbobot |
| `multi_locale.py` | `Faker(["id_ID", "en_US"])` dan bobot per locale |
| `pelanggan_dummy.py` | Versi gabungan: 1.000 pelanggan dummy yang lolos import ke SQLite dan disimpan ke `pelanggan.csv` |
| `tantangan.py` | Jawaban tiga tantangan di akhir artikel: tanggal lahir opsional, kota berbobot, dan kode voucher unik |

## Kebutuhan

- Python 3.10 ke atas (diuji dengan Python 3.14)
- Faker 40 ke atas (diuji dengan Faker 40.43.0)

```
pip install -r requirements.txt
```

## Cara Menjalankan

```
python email_kembar.py
python pelanggan_dummy.py
```

Semua skrip memakai `Faker.seed(2026)`, jadi hasilnya sama setiap kali dijalankan dengan versi Faker yang sama. Versi Faker yang berbeda bisa memberi hasil berbeda.

## Output `email_kembar.py`

```
Gagal di pelanggan ke-294: Puti Siska Manullang, M.Ak <qsaragih@hotmail.com>
IntegrityError: UNIQUE constraint failed: pelanggan.email
```

## Output `pelanggan_dummy.py`

```
Tersimpan di SQLite: 1000 baris
Telepon kosong: 298
Metode bayar: Counter({'Tunai': 599, 'QRIS': 304, 'Transfer': 97})
```
