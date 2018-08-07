package com.mogo.rijogeorge.rijosseekbar

import android.content.res.Resources
import android.util.TypedValue

fun dp2px(dp: Int): Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(),
            Resources.getSystem().displayMetrics).toInt()
}

fun sp2px(sp: Int): Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp.toFloat(),
            Resources.getSystem().displayMetrics).toInt()
}