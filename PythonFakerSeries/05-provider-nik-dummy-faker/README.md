# 5. Provider NIK Dummy yang Lolos Validasi

Bagian dari **Library Faker Python Series** di [ideacak.com](https://www.ideacak.com/search/label/Faker%20Python).

Locale `id_ID` di Faker tidak punya NIK, dan `fake.ssn()` menghasilkan format nomor jaminan sosial Amerika. Artikel ini membuat `NikProvider`, provider custom yang menyusun NIK dummy sesuai Pasal 31 ayat (1) PP 40/2019, lalu mengujinya dengan validator NIK.

| File | Isi |
|---|---|
| `1_nik_asal_angka.py` | Cara naif: 16 angka acak dari `numerify()`, hanya 1 dari 1.000 yang lolos validasi |
| `2_provider_nik.py` | Mendaftarkan `NikProvider` dan membuat NIK dengan jenis kelamin, tanggal lahir, dan kecamatan tertentu |
| `3_cek_validasi.py` | 1.000 NIK unik yang semuanya lolos validasi, plus akibat `maximum_age=100` |
| `4_name_male_campur.py` | Jebakan `name_male()` di `id_ID` yang separuhnya menghasilkan nama perempuan |
| `5_penduduk_konsisten.py` | Data penduduk yang NIK-nya cocok dengan nama, jenis kelamin, tanggal lahir, dan kecamatan |
| `6_pekerjaan_dynamic.py` | `DynamicProvider` untuk daftar pekerjaan berbahasa Indonesia |
| `7_tantangan.py` | Jawaban tiga tantangan di akhir artikel |
| `nik_provider.py` | Class `NikProvider` (diimpor oleh skrip lain) |
| `validasi_nik.py` | Validator NIK ringkas dari artikel Validasi NIK Python (diimpor oleh skrip lain) |
| `kode_kecamatan.csv` | 7.285 kode kecamatan |

`nik_provider.py` dan `validasi_nik.py` tidak diberi nomor karena diimpor oleh skrip lain, dan nama modul Python tidak boleh diawali angka.

## Sumber Data

`kode_kecamatan.csv` diolah dari Kepmendagri Nomor 300.2.2-2138 Tahun 2025 yang dirangkum di
[yonatanyl/KODE-WILAYAH-KEPMENDAGRI-2025](https://github.com/yonatanyl/KODE-WILAYAH-KEPMENDAGRI-2025) (lisensi CC BY 4.0).
File yang sama dipakai di `wilayah/validasi-nik-python/` di repo ini.

## Kebutuhan

- Python 3.10 ke atas (diuji dengan Python 3.14)
- Faker 40 ke atas (diuji dengan Faker 40.43.0)

```
pip install -r requirements.txt
```

## Cara Menjalankan

Jalankan dari dalam folder ini, supaya `nik_provider.py`, `validasi_nik.py`, dan `kode_kecamatan.csv` ditemukan:

```
python 1_nik_asal_angka.py
python 3_cek_validasi.py
```

Semua skrip memakai `Faker.seed(2026)`. Hasil validasi tanggal lahir bergantung pada tanggal hari ini, jadi angka di artikel bisa sedikit bergeser kalau dijalankan bertahun-tahun kemudian.

## Output `3_cek_validasi.py`

```
Lolos validasi: 1000 dari 1.000
NIK berbeda: 1000
Counter({'perempuan': 506, 'laki-laki': 494})
maximum_age=100: Counter({'tanggal lahir ada di masa depan': 2})
```

NIK dummy hanya untuk database uji. NIK yang formatnya valid bisa saja kebetulan sama dengan NIK milik orang sungguhan.
