import pytest


@pytest.fixture(scope="function")
def per_fungsi():
    print("\n  [function] dibuat")


@pytest.fixture(scope="class")
def per_class():
    print("\n  [class] dibuat")


@pytest.fixture(scope="module")
def per_modul():
    print("\n  [module] dibuat")


@pytest.fixture(scope="session")
def per_sesi():
    print("\n  [session] dibuat")


@pytest.fixture
def tidak_autouse():
    print("\n  [autouse=False] dibuat")
