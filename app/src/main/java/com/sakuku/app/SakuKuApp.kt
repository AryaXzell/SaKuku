package com.sakuku.app

import android.app.Application
import com.sakuku.app.data.local.SakuKuDatabase
import com.sakuku.app.data.repository.TransactionRepository

class SakuKuApp : Application() {

    private val database by lazy { SakuKuDatabase.getInstance(this) }

    // DI manual, cukup untuk app sekecil ini (tanpa Hilt/Dagger).
    val repository by lazy { TransactionRepository(database.transactionDao()) }
}
