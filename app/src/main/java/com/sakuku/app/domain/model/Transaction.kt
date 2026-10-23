package com.sakuku.app.domain.model

data class Transaction(
    val id: Long = 0,
    val type: TransactionType,
    val amount: Long,
    val note: String,
    val category: Category,
    val createdAt: Long
)
