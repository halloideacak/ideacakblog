// kasir cukup tahu "berikan subtotal, dapatkan potongan", tidak perlu tahu cara menghitungnya (abstraction)
// hanya punya satu method abstrak, jadi bisa diisi dengan lambda
@FunctionalInterface
interface AturanDiskon {
    int hitung(int subtotal);
}
