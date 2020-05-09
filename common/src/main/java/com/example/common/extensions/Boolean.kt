package com.example.common.extensions

infix fun <T> Boolean.then(param: T): T? = if (this) param else null

infix fun <T> Boolean.then(param: () -> T): T? = if (this) param() else null

fun Boolean.toString(`true`: String, `false`: String) = this then `true` ?: `false`

fun Boolean.toInt(`true`: Int = 1, `false`: Int = 0) = this then `true` ?: `false`

fun Boolean?.orFalse(): Boolean = this ?: false
