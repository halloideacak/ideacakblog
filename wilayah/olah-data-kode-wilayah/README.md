# Olah Data Kode Wilayah pakai Python

Kode untuk artikel ideacak.com *Olah Data Kode Wilayah Kemendagri pakai Python*.

- `01_hitung_manual.py`: hitung jumlah desa, kelurahan, dan desa adat dengan openpyxl dan `Counter`.
- `02_olah_pandas.py`: olah data yang sama dengan pandas (crosstab, groupby, filter).

## Dependency

- `openpyxl`: membaca file `.xlsx` (dipakai langsung di `01_hitung_manual.py`, dan oleh `pd.read_excel()` di `02_olah_pandas.py`).
- `pandas`: mengolah data tabel di `02_olah_pandas.py`.

Diuji dengan Python 3.14, pandas 2.3.3, dan openpyxl 3.1.5.

```
pip install -r requirements.txt
```

## Menyiapkan data

File Excel tidak disertakan di folder ini. Unduh dulu dari repositori
[KODE-WILAYAH-KEPMENDAGRI-2025](https://github.com/yonatanyl/KODE-WILAYAH-KEPMENDAGRI-2025)
oleh yonatanyl (lisensi CC BY 4.0), lalu simpan di folder ini dengan nama
`KODE-WILAYAH-KEPMENDAGRI-2025.xlsx`:

```
curl -L -o KODE-WILAYAH-KEPMENDAGRI-2025.xlsx https://raw.githubusercontent.com/yonatanyl/KODE-WILAYAH-KEPMENDAGRI-2025/main/KODE-WILAYAH-KEPMENDAGRI-2025.xlsx
```
