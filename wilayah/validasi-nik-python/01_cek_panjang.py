"""Cara paling sederhana: cek NIK cuma dari panjang dan isinya angka."""


def nik_valid(nik: str) -> bool:
    return len(nik) == 16 and nik.isdigit()


if __name__ == "__main__":
    contoh = [
        "3201135505980000",  # NIK dummy, format benar
        "320113550598000",   # kurang satu digit
        "3201139905980000",  # tanggal 99, tidak mungkin ada
        "1234567890123456",  # angka asal
    ]
    for nik in contoh:
        print(nik, "->", nik_valid(nik))
