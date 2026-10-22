# Jawaban tantangan artikel 4
from collections import Counter, OrderedDict

from faker import Faker

Faker.seed(2026)
fake = Faker("id_ID")


# Tantangan 1: tanggal lahir yang hanya terisi 60%, umur 17 sampai 70 tahun
def buat_tanggal_lahir():
    tanggal = fake.optional.date_of_birth(prob=0.6, minimum_age=17, maximum_age=70)
    if tanggal is None:
        return None                       # None tidak punya isoformat()
    return tanggal.isoformat()


daftar_tanggal = [buat_tanggal_lahir() for _ in range(1000)]
print("Tanggal lahir kosong:", daftar_tanggal.count(None), "dari 1.000")
print("Contoh:", daftar_tanggal[:4])


# Tantangan 2: Jakarta tiga kali lebih sering daripada kota lain
KOTA = OrderedDict([
    ("Jakarta", 3),
    ("Bandung", 1),
    ("Surabaya", 1),
    ("Medan", 1),
    ("Makassar", 1),
])
print(Counter(fake.random_element(KOTA) for _ in range(7000)))


# Tantangan 3: 50 kode voucher unik berformat VCR-####
kode_voucher = [fake.unique.bothify("VCR-####") for _ in range(50)]
print(len(kode_voucher), "kode,", len(set(kode_voucher)), "berbeda, contoh:", kode_voucher[:3])
