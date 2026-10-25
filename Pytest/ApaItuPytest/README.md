# Pytest Itu Apa? Cara Install, Menulis, dan Running Test di Python

Kode untuk artikel [Pytest Itu Apa?](https://www.ideacak.com/search/label/Pytest) di ideacak.com.

Fungsi `normalisasi_nomor_hp()` mengubah nomor HP Indonesia ke format `62xxxxxxxxxx`. Versi awalnya punya bug (nol di depan tidak dibuang), yang lolos dari pengecekan `print()` tapi langsung ketahuan oleh pytest.

| Folder | Isi |
|---|---|
| `1_cek_manual/` | Fungsi versi awal dan pengecekan dengan `print()` |
| `2_test_pertama/` | Tiga test pertama, **sengaja gagal satu** karena bug versi awal |
| `3_perbaikan/` | Fungsi yang sudah diperbaiki, `pytest.raises`, dan `parametrize` |
| `4_fixture/` | Fixture `scope` dan `autouse`, `conftest.py`, dan `pytest.ini` |
| `5_tantangan/` | Jawaban tantangan: nomor bertanda kurung dan nomor berisi huruf |
| `6_contoh_tambahan/` | Contoh pendukung: test tanpa assert, perbandingan `pytest.raises`, parametrize 3 parameter dan data bertingkat, variabel global vs fixture, fungsi biasa vs fixture `yield`, lima scope dan `autouse`, jangkauan `conftest.py`, dan versi `unittest`. **Beberapa test sengaja gagal** untuk menunjukkan masalahnya |

Setiap folder berdiri sendiri, jadi jalankan pytest dari dalam folder masing-masing.

## Kebutuhan

- Python 3.10 ke atas (diuji dengan Python 3.14)
- pytest 8 ke atas (diuji dengan pytest 9.1.1)

```
pip install -r requirements.txt
```

## Cara Menjalankan

```
cd 1_cek_manual
python cek_manual.py

cd ../2_test_pertama
pytest

cd ../3_perbaikan
pytest -v

cd ../4_fixture
pytest -s

cd ../5_tantangan
pytest -v

cd ../6_contoh_tambahan/6_scope_autouse
pytest -s -q
```

Folder di `6_contoh_tambahan/` juga dijalankan satu per satu dari dalam foldernya. `8_unittest` bisa dijalankan dengan `python -m unittest -v` maupun `pytest`.
