package com.sakuku.app.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/** Hasil query saldo: total pemasukan dan total pengeluaran (semua waktu). */
data class BalanceTuple(
    val totalIn: Long,
    val totalOut: Long
) {
    val balance: Long get() = totalIn - totalOut
}

@Dao
interface TransactionDao {

    @Query("SELECT * FROM transactions ORDER BY createdAt DESC, id DESC LIMIT :limit")
    fun getRecent(limit: Int): Flow<List<TransactionEntity>>

    @Query("SELECT * FROM transactions ORDER BY createdAt DESC, id DESC")
    fun getAll(): Flow<List<TransactionEntity>>

    /** Rentang waktu [startMillis, endMillis). Repository yang menghitung batas bulannya. */
    @Query(
        "SELECT * FROM transactions " +
            "WHERE createdAt >= :startMillis AND createdAt < :endMillis " +
            "ORDER BY createdAt DESC, id DESC"
    )
    fun getByRange(startMillis: Long, endMillis: Long): Flow<List<TransactionEntity>>

    @Query(
        "SELECT " +
            "COALESCE(SUM(CASE WHEN type = 'INCOME' THEN amount ELSE 0 END), 0) AS totalIn, " +
            "COALESCE(SUM(CASE WHEN type = 'EXPENSE' THEN amount ELSE 0 END), 0) AS totalOut " +
            "FROM transactions"
    )
    fun getBalance(): Flow<BalanceTuple>

    // REPLACE supaya fitur Undo bisa memasukkan lagi transaksi dengan id yang sama.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: TransactionEntity): Long

    @Query("DELETE FROM transactions WHERE id = :id")
    suspend fun delete(id: Long)

    /** Dipakai untuk "Reset semua data" di Settings. */
    @Query("DELETE FROM transactions")
    suspend fun deleteAll()
}
