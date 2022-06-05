package com.sladictilen.moviedatabase.util.helpers

import com.sladictilen.moviedatabase.R

fun getUserRatingImage(rating: String): Int {
    return when (rating) {
        "1" -> R.drawable.ic_very_bad2
        "2" -> R.drawable.ic_bad2
        "3" -> R.drawable.ic_okay
        "4" -> R.drawable.ic_good2
        "5" -> R.drawable.ic_excellent2
        else -> 0
    }
}