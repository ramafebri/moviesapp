package com.mandiri.moviesapp.common

import java.text.SimpleDateFormat
import java.util.*

object DateHelper {
    private val APPS_LOCALE = Locale("in", "ID")
    const val DATE_TIME_FORMAT_DISPLAY = "dd MMM yyyy"

    fun dateParseToString(date: Date?, newFormat: String): String? {
        return if (date != null) {
            SimpleDateFormat(newFormat, APPS_LOCALE).format(date)
        } else {
            null
        }
    }
}