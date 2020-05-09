package com.example.common.extensions

import android.content.res.TypedArray
import androidx.annotation.StyleableRes

fun TypedArray.getDIP(@StyleableRes index: Int, defValue: Float) =
    getString(index)?.replace("dip", "", true)?.toFloatOrNull() ?: defValue
