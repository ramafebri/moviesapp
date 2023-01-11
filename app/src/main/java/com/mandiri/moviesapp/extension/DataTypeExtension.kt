package com.mandiri.moviesapp.extension

fun Int?.orZero() = this ?: 0
fun Float?.orZero() = this ?: 0.0f
fun Boolean?.orFalse() = this ?: false