package com.example.carnet.IdentificacionMascotas


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun ScreenB(
    name: String,
    breed: String,
    size: String,
    age: Int,
    photoUrl: String,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                AsyncImage(
                    model = photoUrl,
                    contentDescription = "Foto mascota",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
                Spacer(Modifier.height(8.dp))
                Text("Nombre: $name")
                Text("Raza: $breed")
                Text("Tamaño: $size")
                Text("Edad: $age")
            }
        }

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = onBack,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Volver")
        }
    }
}
