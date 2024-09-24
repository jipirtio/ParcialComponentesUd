package com.ud.Viajes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ud.Viajes.navigation.NavegacionApp
import com.ud.Viajes.ui.theme.ParcialViajesTheme

/**
 * Actividad principal de la aplicación.
 */
class MainActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ParcialViajesTheme {
                NavegacionApp()
            }
        }
    }
}