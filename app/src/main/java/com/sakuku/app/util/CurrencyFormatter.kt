package com.sakuku.app.util

import com.sakuku.app.domain.model.TransactionType

object CurrencyFormatter {

    // 12 digit sudah sampai ratusan miliar, aman dari overflow Long.
    private const val MAX_DIGITS = 12

    /** 15000 -> "15.000" (tanpa "Rp"). Dipakai untuk format live di TextField. */
    fun formatThousands(amount: Long): String =
        amount.toString().reversed().chunked(3).joinToString(".").reversed()

    /** 15000 -> "Rp15.000". Nilai negatif ditulis "-Rp15.000". */
    fun formatRupiah(amount: Long): String =
        if (amount < 0) "-Rp${formatThousands(-amount)}" else "Rp${formatThousands(amount)}"

    /** Pengeluaran -> "-Rp15.000", pemasukan -> "+Rp200.000". */
    fun formatSigned(amount: Long, type: TransactionType): String {
        val sign = if (type == TransactionType.INCOME) "+" else "-"
        return "$sign${formatRupiah(amount)}"
    }

    /**
     * Ambil angka dari teks yang diketik user ("15.000", "15000", "1a5") jadi Long.
     * Karakter selain angka dibuang, panjangnya dibatasi, kosong -> 0.
     */
    fun parseDigits(input: String): Long =
        input.filter { it.isDigit() }.take(MAX_DIGITS).toLongOrNull() ?: 0L
}
