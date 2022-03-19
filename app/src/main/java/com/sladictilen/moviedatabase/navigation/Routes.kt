package com.sladictilen.moviedatabase.navigation

import android.graphics.drawable.VectorDrawable
import androidx.annotation.StringRes
import com.sladictilen.moviedatabase.R

sealed class Screens(val route: String, val icon: VectorDrawable ){
    object DiscoveScreen : Screens("discover", R.string.)
}
