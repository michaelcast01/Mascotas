// app/src/main/java/com/example/carnet/MainActivity.kt
package com.example.carnet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.carnet.IdentificacionMascotas.NavGraph
import com.example.carnet.ui.theme.CarnetTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CarnetTheme {
                NavGraph()
            }
        }
    }
}
