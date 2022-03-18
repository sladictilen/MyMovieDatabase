package com.sladictilen.moviedatabase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.sladictilen.moviedatabase.navigation.Navigation
import com.sladictilen.moviedatabase.ui.theme.MovieDatabaseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieDatabaseTheme {
                Navigation()
            }
        }
    }
}
