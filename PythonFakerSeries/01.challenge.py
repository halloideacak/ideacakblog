'''
    Sebagai latihan awal, coba buat script Python yang menghasilkan 10 data user palsu berisi:
    - Nama 
    - Email
    - Alamat
    - Nomor telepon
    Gunakan Faker('id_ID') agar lebih sesuai dengan data lokal.
'''
from faker import Faker

fake = Faker('id_ID')

for i in range(10):
    print("-----------------")
    print("data ke " + str(i+1))
    print(fake.name())
    print(fake.email())
    print(fake.address())
    print(fake.phone_number())
    print("-----------------")
