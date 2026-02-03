package it.inps.sirio.utils

import java.util.Calendar

object SirioDateHelpers {
    /** Ritorna i millis di oggi (senza orario, ore/min/sec azzerati) */
    fun today(): Long {
        val cal = Calendar.getInstance()
        cal.set(Calendar.HOUR_OF_DAY, 0)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MILLISECOND, 0)
        return cal.timeInMillis
    }

    /** Aggiunge o sottrae giorni */
    fun addDays(days: Int): Long {
        val cal = Calendar.getInstance()
        cal.add(Calendar.DAY_OF_MONTH, days)
        cal.set(Calendar.HOUR_OF_DAY, 0)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MILLISECOND, 0)
        return cal.timeInMillis
    }

    /** Aggiunge o sottrae mesi */
    fun addMonths(months: Int): Long {
        val cal = Calendar.getInstance()
        cal.add(Calendar.MONTH, months)
        cal.set(Calendar.HOUR_OF_DAY, 0)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MILLISECOND, 0)
        return cal.timeInMillis
    }

    /** Aggiunge o sottrae anni */
    fun addYears(years: Int): Long {
        val cal = Calendar.getInstance()
        cal.add(Calendar.YEAR, years)
        cal.set(Calendar.HOUR_OF_DAY, 0)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MILLISECOND, 0)
        return cal.timeInMillis
    }
}
