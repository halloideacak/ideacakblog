# 17. Membuat REST API Sederhana dengan Spring Boot

Bagian dari **Seri Belajar Pemrograman Java Studi Kasus Warteg Canggih** di [ideacak.com](https://www.ideacak.com/search/label/Warteg%20Canggih%20Series).

Penutup seri. Kasir Warteg Canggih bisa diakses lewat REST API: daftar menu dibaca dengan `GET`, pesanan dikirim dengan `POST` dalam bentuk JSON, dan exception kasir diubah jadi kode status HTTP oleh `@RestControllerAdvice`.

## Endpoint

| Method | Alamat | Kegunaan | Jawaban berhasil |
|---|---|---|---|
| `GET` | `/api/menu` | Semua menu dan stoknya | `200` |
| `GET` | `/api/menu/{nomor}` | Satu menu | `200` |
| `POST` | `/api/pesanan` | Mengirim pesanan | `201` + struk |

Jawaban gagal berbentuk `{"pesan": "..."}` dengan kode status `404` (menu tidak ada), `400` (porsi di luar 1 sampai 20, pesanan kosong, nomor menu salah), atau `409` (stok tidak cukup).

## Isi Proyek

- `pom.xml`: dependensi `spring-boot-starter-webmvc` (Spring Web).
- `mvnw`, `mvnw.cmd`, `.mvn/`: Maven Wrapper.
- `data/menu.txt`: daftar menu dan stok awal.
- `contoh/pesanan-budi.json`, `contoh/pesanan-siti.json`: contoh isi permintaan POST.
- `src/main/java/com/ideacak/warteg/`
  - `WartegCanggihApplication.java`: titik awal aplikasi.
  - `KasirController.java`: tiga endpoint (`@RestController`).
  - `PenanganError.java`: exception ke kode status (`@RestControllerAdvice`).
  - `KasirService.java`: validasi, stok, diskon, dan struk (`@Service`).
  - `ArsipWarteg.java`: membaca `menu.txt` (`@Component`).
  - `ItemPesanan.java`, `PesananRequest.java`, `MenuResponse.java`, `Struk.java`, `PesanError.java`: record untuk data JSON.
  - `Menu.java`, `PesananTidakValidException.java`, `StokTidakCukupException.java`, `MenuTidakDitemukanException.java`.

## Kebutuhan

- JDK 21 (misalnya [Eclipse Temurin 21](https://adoptium.net/temurin/releases)), dengan `JAVA_HOME` menunjuk ke JDK tersebut.
- Koneksi internet saat pertama kali dijalankan.
- `curl` untuk mencoba POST (sudah ada di Windows 10 ke atas, macOS, dan kebanyakan Linux). Di PowerShell, ketik `curl.exe`.

## Cara Menjalankan

Dari folder ini:

```
./mvnw spring-boot:run
```

Di Windows Command Prompt: `mvnw spring-boot:run`. Server menyala di `http://localhost:8080`. Hentikan dengan Ctrl+C.

## Mencoba API

Lihat semua menu (bisa juga dibuka di browser):

```
curl http://localhost:8080/api/menu
```

Kirim pesanan Budi:

```
curl -X POST http://localhost:8080/api/pesanan -H "Content-Type: application/json" -d @contoh/pesanan-budi.json
```

```json
{"namaPembeli":"Budi","pesanan":[{"menu":"Tempe orek","porsi":2,"harga":8000},{"menu":"Telur balado","porsi":2,"harga":12000}],"subtotal":20000,"diskon":2000,"total":18000}
```

Kirim pesanan Siti (stok telur balado tinggal 1, jadi ditolak):

```
curl -i -X POST http://localhost:8080/api/pesanan -H "Content-Type: application/json" -d @contoh/pesanan-siti.json
```

```
HTTP/1.1 409
...
{"pesan":"Stok Telur balado tinggal 1, tidak cukup untuk 2 porsi."}
```

Menu yang tidak ada:

```
curl -i http://localhost:8080/api/menu/9
```

```
HTTP/1.1 404
...
{"pesan":"Menu nomor 9 tidak ada."}
```

Stok hanya disimpan di memori, jadi kembali seperti isi `data/menu.txt` setiap aplikasi dijalankan ulang.
