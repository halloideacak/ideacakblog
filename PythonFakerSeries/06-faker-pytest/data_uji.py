# Pembuat data pendaftar dummy untuk test
FORMAT_SELULER = ("08##########", "08##-####-####", "08## #### ####", "+628##########")


def buat_pendaftar(fake):
    """Versi pertama: memakai phone_number() bawaan Faker."""
    return {
        "nama": fake.name(),
        "email": fake.email(),
        "telepon": fake.optional.phone_number(prob=0.7),
        "tanggal_lahir": fake.date_of_birth(minimum_age=17, maximum_age=80),
    }


def buat_pendaftar_realistis(fake):
    """Versi kedua: nomor seluler dengan format yang memang dipakai orang Indonesia."""
    tanggal_lahir = fake.date_of_birth(minimum_age=17, maximum_age=80)
    return {
        "nama": fake.name(),
        "email": fake.email(),
        "telepon": fake.optional.numerify(fake.random_element(FORMAT_SELULER), prob=0.7),
        "tanggal_lahir": tanggal_lahir,
        "nik": fake.nik(tanggal_lahir=tanggal_lahir),
    }
