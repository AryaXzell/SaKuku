package com.sakuku.app.data.repository

import com.sakuku.app.data.local.BalanceTuple
import com.sakuku.app.data.local.TransactionDao
import com.sakuku.app.data.local.toDomain
import com.sakuku.app.data.local.toEntity
import com.sakuku.app.domain.model.Transaction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Calendar

/** Single source of truth untuk data transaksi. ViewModel cukup ngobrol sama class ini. */
class TransactionRepository(private val dao: TransactionDao) {

    fun getRecent(limit: Int = 10): Flow<List<Transaction>> =
        dao.getRecent(limit).map { list -> list.map { it.toDomain() } }

    fun getAll(): Flow<List<Transaction>> =
        dao.getAll().map { list -> list.map { it.toDomain() } }

    /** [month] pakai 1..12 (Januari = 1). */
    fun getByMonth(year: Int, month: Int): Flow<List<Transaction>> {
        val start = Calendar.getInstance().apply {
            clear()
            set(year, month - 1, 1, 0, 0, 0)
        }
        val end = (start.clone() as Calendar).apply { add(Calendar.MONTH, 1) }
        return dao.getByRange(start.timeInMillis, end.timeInMillis)
            .map { list -> list.map { it.toDomain() } }
    }

    fun getBalance(): Flow<BalanceTuple> = dao.getBalance()

    suspend fun add(transaction: Transaction): Long = dao.insert(transaction.toEntity())

    /** Masukin lagi transaksi yang barusan dihapus (id sama), untuk Undo. */
    suspend fun restore(transaction: Transaction) {
        dao.insert(transaction.toEntity())
    }

    suspend fun delete(id: Long) = dao.delete(id)

    suspend fun deleteAll() = dao.deleteAll()
}
