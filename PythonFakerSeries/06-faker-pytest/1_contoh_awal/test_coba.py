# Tanpa conftest.py: fixture faker memakai en_US dan di-seed 0 sebelum setiap test
def test_pertama(faker):
    print("\ntest_pertama:", faker.name())


def test_kedua(faker):
    print("\ntest_kedua:", faker.name())
