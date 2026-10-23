package com.sakuku.app.util

import android.content.Context
import com.sakuku.app.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object DateFormatter {

    private val localeId: Locale = Locale.forLanguageTag("id-ID")

    /** Label header grup di Riwayat: "Hari Ini", "Kemarin", atau "12 Okt 2026". */
    fun groupLabel(context: Context, millis: Long, now: Long = System.currentTimeMillis()): String {
        val daysAgo = daysBetween(millis, now)
        return when (daysAgo) {
            0 -> context.getString(R.string.date_today)
            1 -> context.getString(R.string.date_yesterday)
            else -> SimpleDateFormat("d MMM yyyy", localeId).format(Date(millis))
        }
    }

    /** Label bulan untuk MonthPicker, contoh "Oktober 2026". [month] pakai 1..12. */
    fun monthLabel(year: Int, month: Int): String {
        val cal = Calendar.getInstance().apply {
            clear()
            set(year, month - 1, 1)
        }
        return SimpleDateFormat("MMMM yyyy", localeId).format(cal.time)
    }

    /** Awal hari (00:00) dari [millis], dipakai untuk mengelompokkan transaksi per tanggal. */
    fun startOfDay(millis: Long): Long =
        Calendar.getInstance().apply {
            timeInMillis = millis
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.timeInMillis

    /** Selisih hari kalender antara [millis] dan [now]. 0 = hari ini, 1 = kemarin. */
    private fun daysBetween(millis: Long, now: Long): Int {
        val a = Calendar.getInstance().apply { timeInMillis = startOfDay(millis) }
        val b = Calendar.getInstance().apply { timeInMillis = startOfDay(now) }
        var days = 0
        // Loop kecil ini aman dari masalah DST dan hasilnya bukan perkiraan.
        while (a.before(b) && days < 3) {
            a.add(Calendar.DAY_OF_YEAR, 1)
            days++
        }
        return days
    }
}
