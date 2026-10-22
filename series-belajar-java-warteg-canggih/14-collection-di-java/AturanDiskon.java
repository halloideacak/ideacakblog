// kasir cukup tahu "berikan subtotal, dapatkan potongan", tidak perlu tahu cara menghitungnya (abstraction)
interface AturanDiskon {
    int hitung(int subtotal);
}
