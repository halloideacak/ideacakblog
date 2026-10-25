# 6. Faker di pytest dengan Fixture faker

Bagian dari **Library Faker Python Series** di [ideacak.com](https://www.ideacak.com/search/label/Faker%20Python).

Validasi form pendaftaran diuji dengan data dari fixture `faker`. Test dengan satu data tetap lolos, tapi data Faker langsung menemukan nama bergelar yang ditolak dan format telepon yang tidak cocok.

| File | Isi |
|---|---|
| `pendaftaran_awal.py` | Validator versi awal, masih menolak nama bergelar dan telepon berformat |
| `pendaftaran.py` | Validator versi perbaikan, plus pengecekan NIK |
| `data_uji.py` | `buat_pendaftar()` dan `buat_pendaftar_realistis()` |
| `nik_provider.py`, `validasi_nik.py`, `kode_kecamatan.csv` | Dari bagian 5 |
| `pytest.ini` | `pythonpath = .` supaya test di subfolder bisa mengimpor modul di folder ini |
| `1_contoh_awal/test_coba.py` | Tanpa `conftest.py`: fixture `faker` memakai `en_US` dan seed 0 |
| `2_tests/conftest.py` | `faker_session_locale` dan pendaftaran `NikProvider` |
| `2_tests/test_1_asal_fixture.py` | Fixture `faker` sama dengan `Faker("id_ID")` yang di-seed 0, plus contoh `faker_seed` dan `faker_locale` |
| `2_tests/test_2_data_tetap.py` | Satu data tetap, lolos |
| `2_tests/test_3_pendaftaran_awal.py` | Data Faker ke validator awal, **sengaja gagal**, plus jebakan `parametrize` |
| `2_tests/test_4_pendaftaran.py` | 200 pendaftar realistis ke validator perbaikan, semua lolos |
| `3_tantangan/` | Jawaban empat tantangan, dengan `faker_seed` 2026 di `conftest.py`-nya. Tantangan 4 memakai `faker_locale` multi-locale, jadi `NikProvider` dipasang lewat `faker["id_ID"]` |

Nama file test harus diawali `test_` supaya ditemukan pytest, jadi nomornya diletakkan setelah awalan itu.

## Kebutuhan

- Python 3.10 ke atas (diuji dengan Python 3.14)
- Faker 40 ke atas (diuji dengan Faker 40.43.0) dan pytest 8 ke atas (diuji dengan pytest 9.1.1)

```
pip install -r requirements.txt
```

## Cara Menjalankan

Dari dalam folder ini:

```
pytest 1_contoh_awal -s
pytest 2_tests/test_1_asal_fixture.py -q -s
pytest 2_tests/test_4_pendaftaran.py -q -s
pytest 3_tantangan -q -s
```

`pytest 2_tests` akan menampilkan 1 test gagal (`test_pendaftar_faker_lolos`). Itu disengaja, sebagai bukti bug di `pendaftaran_awal.py`.

Jalankan per folder, jangan `pytest` dari folder ini tanpa argumen. Object Faker milik sesi dibuat sekali oleh test pertama, dan `1_contoh_awal` (tanpa `conftest.py`) membuatnya dengan `en_US`. Akibatnya, `2_tests` dan `3_tantangan` ikut memakai `en_US`, dan test di sana gagal atau error.

## Output `pytest 2_tests/test_4_pendaftaran.py -q -s`

```
nama dengan gelar atau tanda baca: 845 dari 2.000, contoh: ['drg. Vanya Andriani, S.Gz', 'Tgk. Patricia Suartini', 'Belinda Sitompul, M.Kom.']
phone_number() yang bukan nomor seluler 08: 852 dari 1.000, contoh: ['(060) 487 6475', '+62 (0115) 781-5659', '+62-97-535-1393']
203 passed
```
