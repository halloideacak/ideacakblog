# Penyamar konsisten: seed Faker diturunkan dari nilai asli + kunci rahasia
import hashlib
import hmac
import os

from faker import Faker

# kunci rahasia dibaca dari environment variable, jangan ditulis di kode
KUNCI = os.environ.get("KUNCI_SAMARAN", "contoh-kunci-jangan-dipakai").encode()

fake = Faker("id_ID")


def sidik(nilai, jenis):
    """HMAC-SHA256 dari nilai asli. `jenis` membuat nama dan email yang sama tetap beda hasil."""
    return hmac.new(KUNCI, f"{jenis}:{nilai}".encode(), hashlib.sha256).digest()


def pakai_seed_dari(nilai, jenis):
    fake.seed_instance(int.from_bytes(sidik(nilai, jenis)[:8], "big"))


def samarkan_nama(nama):
    pakai_seed_dari(nama, "nama")
    return fake.name()


def samarkan_email(email):
    pakai_seed_dari(email, "email")
    # 6 karakter sidik ditempel supaya dua email asli tidak jatuh ke email palsu yang sama
    return f"{fake.user_name()}.{sidik(email, 'email').hex()[:6]}@example.com"


def samarkan_telepon(telepon):
    pakai_seed_dari(telepon, "telepon")
    return fake.numerify("08##########")
