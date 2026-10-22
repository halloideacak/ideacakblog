# 7. Isi Database SQLite dan Analisis dengan pandas

Bagian dari **Library Faker Python Series** di [ideacak.com](https://www.ideacak.com/search/label/Faker%20Python).

Transaksi dummy dengan sebaran merata membuat toko terlihat ramai jam 3 pagi. Artikel ini membuat 1.000 pelanggan dan 20.000 transaksi berelasi dengan jam ramai, nominal, dan pelanggan langganan yang berbobot, memasukkannya ke SQLite dengan `executemany`, lalu membuktikan polanya dengan pandas.

| File | Isi |
|---|---|
| `toko.py` | Skema tabel, bobot jam dan nominal, `buat_pelanggan()`, `buat_transaksi()`, dan `isi_database()` (diimpor oleh skrip lain) |
| `1_transaksi_naif.py` | Cara naif: waktu dan nominal acak merata |
| `2_insert_lambat_vs_cepat.py` | Commit per baris vs `executemany` dalam satu transaksi (butuh waktu sekitar satu menit) |
| `3_isi_toko.py` | Membuat `toko.db` dan menguji foreign key |
| `4_analisis_pandas.py` | Membaca `toko.db` dengan pandas: transaksi per jam, omzet per metode bayar, nominal, pelanggan teratas |
| `5_tantangan.py` | Jawaban tiga tantangan di akhir artikel |

`toko.db` tidak ikut di repo (`*.db` di-ignore). Jalankan `3_isi_toko.py` untuk membuatnya.

## Kebutuhan

- Python 3.10 ke atas (diuji dengan Python 3.14 dan SQLite 3.50.4)
- Faker 40 ke atas (diuji dengan Faker 40.43.0)
- pandas 2 ke atas (diuji dengan pandas 3.1.0)

```
pip install -r requirements.txt
```

## Cara Menjalankan

```
python 1_transaksi_naif.py
python 2_insert_lambat_vs_cepat.py
python 3_isi_toko.py
python 4_analisis_pandas.py
python 5_tantangan.py
```

## Output `2_insert_lambat_vs_cepat.py`

Angkanya bergantung pada kecepatan disk komputermu.

```
Commit per baris : 45.16 detik
executemany      : 0.03 detik
```

## Output `3_isi_toko.py`

```
Pelanggan: 1000
Transaksi: 20000
[(1, 502, '2026-09-29 20:45:00', 120000, 'Tunai'), (2, 790, '2026-09-25 11:55:00', 139000, 'Tunai'), (3, 742, '2026-09-28 14:32:00', 7500, 'Tunai')]
IntegrityError: FOREIGN KEY constraint failed
```
