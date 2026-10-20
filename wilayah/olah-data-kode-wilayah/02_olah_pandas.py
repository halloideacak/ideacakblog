"""Olah data kode wilayah Kepmendagri 2025 dengan pandas."""
from pathlib import Path

import pandas as pd

FILE = Path(__file__).with_name("KODE-WILAYAH-KEPMENDAGRI-2025.xlsx")

df = pd.read_excel(FILE, dtype=str)
df.columns = ["kode", "nama", "kode_kec", "kecamatan", "kode_kab", "kabupaten_kota",
              "kode_prov", "provinsi", "tipe"]

# 1. Jumlah per jenis
print(df["tipe"].value_counts(), "\n")

# 2. Desa vs kelurahan per provinsi, 5 provinsi dengan kelurahan terbanyak
per_prov = pd.crosstab(df["provinsi"], df["tipe"])
print(per_prov.sort_values("KELURAHAN", ascending=False).head(5), "\n")

# 3. Kabupaten atau kota? Lihat digit ketiga-keempat kode (Permendagri 58/2021 Pasal 6)
df["jenis_daerah"] = df["kode_kab"].str[-2:].astype(int).map(lambda n: "kota" if n >= 71 else "kabupaten")
print(df.drop_duplicates("kode_kab")["jenis_daerah"].value_counts(), "\n")

# 4. Kota yang punya desa
kota_desa = df[(df["jenis_daerah"] == "kota") & (df["tipe"] != "KELURAHAN")]
print(kota_desa["kabupaten_kota"].value_counts().head(5), "\n")

# 5. Persentase nama desa/kelurahan berawalan "Ci" per provinsi
df["awalan_ci"] = df["nama"].str.lower().str.startswith("ci")
persen_ci = (df.groupby("provinsi")["awalan_ci"].mean() * 100).round(1)
print(persen_ci.sort_values(ascending=False).head(5), "\n")

# 6. Contoh nama berawalan "Ci" di Kabupaten Bogor
ci = df[df["awalan_ci"]]
contoh = ci[ci["kabupaten_kota"] == "Kabupaten Bogor"]
print(contoh[["kode", "nama", "kecamatan"]].head(10).to_string(index=False), "\n")

# 7. Nama berawalan "Ci" yang paling sering dipakai di seluruh Indonesia
#    (perhatikan "Cinta Damai": ikut terhitung walau bukan dari kata "cai")
print(ci["nama"].value_counts().head(10))
