package com.sakuku.app.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sakuku.app.domain.model.Category
import com.sakuku.app.domain.model.Transaction
import com.sakuku.app.domain.model.TransactionType

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val type: String,       // "INCOME" atau "EXPENSE"
    val amount: Long,       // dalam rupiah, integer
    val note: String,
    val category: String,   // contoh: "🍜 Jajan"
    val createdAt: Long     // System.currentTimeMillis()
)

fun TransactionEntity.toDomain(): Transaction = Transaction(
    id = id,
    type = TransactionType.valueOf(type),
    amount = amount,
    note = note,
    category = Category.fromLabel(category),
    createdAt = createdAt
)

fun Transaction.toEntity(): TransactionEntity = TransactionEntity(
    id = id,
    type = type.name,
    amount = amount,
    note = note,
    category = category.label,
    createdAt = createdAt
)
