# Validasi NIK pakai Python

Kode untuk artikel ideacak.com *Validasi NIK pakai Python*.

- `01_cek_panjang.py`: cek panjang dan isi angka saja.
- `02_bedah_nik.py`: bedah NIK per kelompok digit dan cek tanggal lahir dengan `datetime`.
- `03_validasi_lengkap.py`: validasi lengkap, termasuk mencocokkan kode kecamatan ke `kode_kecamatan.csv`.

Butuh Python 3.10 atau lebih baru. Semua contoh NIK di sini adalah data dummy.

## Sumber data

`kode_kecamatan.csv` (7.285 kecamatan) diolah dari
[KODE-WILAYAH-KEPMENDAGRI-2025](https://github.com/yonatanyl/KODE-WILAYAH-KEPMENDAGRI-2025)
oleh yonatanyl (lisensi CC BY 4.0), yang merangkum Kepmendagri Nomor 300.2.2-2138 Tahun 2025.
Kolom yang diambil: kode kecamatan (tanpa titik), nama kecamatan, kabupaten/kota, dan provinsi.
