# Provider Faker untuk NIK dummy yang formatnya sesuai Pasal 31 ayat (1) PP 40/2019
import csv
from pathlib import Path

from faker.providers import BaseProvider

FILE_KECAMATAN = Path(__file__).with_name("kode_kecamatan.csv")

with open(FILE_KECAMATAN, encoding="utf-8") as f:
    KODE_KECAMATAN = tuple(baris["kode"] for baris in csv.DictReader(f))


class NikProvider(BaseProvider):
    def nik(self, jenis_kelamin=None, tanggal_lahir=None, kode_kecamatan=None):
        # 6 digit pertama: kode kecamatan yang benar-benar ada
        kode = kode_kecamatan or self.random_element(KODE_KECAMATAN)

        # 6 digit kedua: tanggal lahir DDMMYY, perempuan ditambah 40
        jenis_kelamin = jenis_kelamin or self.random_element(("laki-laki", "perempuan"))
        tanggal_lahir = tanggal_lahir or self.generator.date_of_birth(minimum_age=17, maximum_age=80)
        hari = tanggal_lahir.day + (40 if jenis_kelamin == "perempuan" else 0)
        tanggal = f"{hari:02d}{tanggal_lahir:%m%y}"

        # 4 digit terakhir: nomor urut
        nomor_urut = f"{self.random_int(1, 9999):04d}"

        return kode + tanggal + nomor_urut
