import unittest

from nomor_hp import normalisasi_nomor_hp


class TestNormalisasiNomorHp(unittest.TestCase):
    def setUp(self):
        self.nomor_target = "6281234567890"

    def test_awalan_nol(self):
        self.assertEqual(normalisasi_nomor_hp("0812-3456-7890"), self.nomor_target)

    def test_format_internasional(self):
        self.assertEqual(normalisasi_nomor_hp("+62 812 3456 7890"), self.nomor_target)

    def test_nomor_terlalu_pendek(self):
        with self.assertRaises(ValueError):
            normalisasi_nomor_hp("0812")


if __name__ == "__main__":
    unittest.main()
