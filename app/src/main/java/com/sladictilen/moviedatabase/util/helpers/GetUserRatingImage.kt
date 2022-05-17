package com.sladictilen.moviedatabase.util.helpers

import com.sladictilen.moviedatabase.R

fun getUserRatingImage(rating: String): Int {
    return when (rating) {
        "1" -> R.drawable.ic_very_bad
        "2" -> R.drawable.ic_bad
        "3" -> R.drawable.ic_okay
        "4" -> R.drawable.ic_great
        "5" -> R.drawable.ic_excelent
        else -> 0
    }
}