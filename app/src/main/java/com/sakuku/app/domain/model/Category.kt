package com.sakuku.app.domain.model

import androidx.annotation.StringRes
import com.sakuku.app.R

/**
 * Kategori transaksi.
 *
 * [label] adalah nilai yang disimpan di database (kolom `category`), contoh "🍜 Jajan".
 * Nama yang tampil di UI diambil dari strings.xml lewat [nameRes].
 * [colorArgb] dipakai untuk chip dan pie chart.
 */
enum class Category(
    val label: String,
    val emoji: String,
    @StringRes val nameRes: Int,
    val colorArgb: Long,
    val keywords: List<String>
) {
    JAJAN(
        label = "🍜 Jajan",
        emoji = "🍜",
        nameRes = R.string.category_jajan,
        colorArgb = 0xFFFFA726,
        keywords = listOf(
            "jajan", "makan", "minum", "bakso", "mie", "kopi", "snack", "es",
            "gorengan", "kantin", "ciki", "roti", "nasi", "ayam", "sate", "boba"
        )
    ),
    TRANSPORT(
        label = "🚌 Transport",
        emoji = "🚌",
        nameRes = R.string.category_transport,
        colorArgb = 0xFF42A5F5,
        keywords = listOf(
            "transport", "ojek", "angkot", "bensin", "grab", "gojek", "bus",
            "parkir", "ongkir", "kereta", "taksi"
        )
    ),
    KUOTA(
        label = "📱 Kuota",
        emoji = "📱",
        nameRes = R.string.category_kuota,
        colorArgb = 0xFF7E57C2,
        keywords = listOf("kuota", "pulsa", "internet", "paket data", "wifi")
    ),
    BUKU(
        label = "📚 Buku",
        emoji = "📚",
        nameRes = R.string.category_buku,
        colorArgb = 0xFF8D6E63,
        keywords = listOf(
            "buku", "alat tulis", "pensil", "pulpen", "fotokopi", "print", "atk", "map"
        )
    ),
    HIBURAN(
        label = "🎮 Hiburan",
        emoji = "🎮",
        nameRes = R.string.category_hiburan,
        colorArgb = 0xFFEC407A,
        keywords = listOf(
            "game", "nonton", "bioskop", "topup", "top up", "ml", "ff", "steam",
            "spotify", "konser"
        )
    ),
    TABUNGAN(
        label = "💰 Tabungan",
        emoji = "💰",
        nameRes = R.string.category_tabungan,
        colorArgb = 0xFF66BB6A,
        keywords = listOf("nabung", "tabung", "simpan")
    ),
    LAINNYA(
        label = "📦 Lainnya",
        emoji = "📦",
        nameRes = R.string.category_lainnya,
        colorArgb = 0xFF78909C,
        keywords = emptyList()
    ),
    PEMASUKAN(
        label = "💵 Pemasukan",
        emoji = "💵",
        nameRes = R.string.category_pemasukan,
        colorArgb = 0xFF00897B,
        keywords = emptyList()
    );

    companion object {
        /** Kategori untuk pengeluaran, urutannya sama dengan urutan pengecekan keyword. */
        val expenseCategories: List<Category> = entries.filter { it != PEMASUKAN }

        /** Balik dari nilai di database ke enum. Kalau nggak dikenal, jatuh ke [LAINNYA]. */
        fun fromLabel(label: String): Category =
            entries.firstOrNull { it.label == label } ?: LAINNYA
    }
}
