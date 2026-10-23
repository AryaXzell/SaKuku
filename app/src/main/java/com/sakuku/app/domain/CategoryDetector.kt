package com.sakuku.app.domain

import com.sakuku.app.domain.model.Category
import com.sakuku.app.domain.model.TransactionType
import java.util.Locale

/** Nebak kategori dari keterangan. Pencocokan substring, case-insensitive, urutan sesuai [Category.expenseCategories]. */
object CategoryDetector {

    fun detect(type: TransactionType, note: String): Category {
        if (type == TransactionType.INCOME) return Category.PEMASUKAN

        val text = note.lowercase(Locale.ROOT)
        return Category.expenseCategories.firstOrNull { category ->
            category.keywords.any { keyword -> text.contains(keyword) }
        } ?: Category.LAINNYA
    }
}
