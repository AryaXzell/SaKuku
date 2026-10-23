package com.sakuku.app.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [TransactionEntity::class],
    version = 1,
    exportSchema = false
)
abstract class SakuKuDatabase : RoomDatabase() {

    abstract fun transactionDao(): TransactionDao

    companion object {
        @Volatile
        private var instance: SakuKuDatabase? = null

        fun getInstance(context: Context): SakuKuDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    SakuKuDatabase::class.java,
                    "sakuku.db"
                ).build().also { instance = it }
            }
    }
}
