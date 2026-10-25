def normalisasi_nomor_hp(nomor):
    """Ubah nomor HP Indonesia ke format 62xxxxxxxxxx."""
    angka = nomor.replace("-", "").replace(" ", "")
    if angka.startswith("+"):
        angka = angka[1:]
    if angka.startswith("0"):
        angka = "62" + angka[1:]
    if not angka.isdigit() or not 11 <= len(angka) <= 14:
        raise ValueError(f"Nomor HP tidak valid: {nomor}")
    return angka
