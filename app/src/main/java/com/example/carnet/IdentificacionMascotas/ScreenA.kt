// app/src/main/java/com/example/carnet/IdentificacionMascotas/ScreenA.kt
package com.example.carnet.IdentificacionMascotas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Photo
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenA(
    onRegister: (name: String, breed: String, size: String, age: Int, photoUrl: String) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Registro Mascota", color = Color.White) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        },
        containerColor = Color.Transparent
    ) { padding ->
        Box(
            Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        0.0f to Color(0xFF00BFA6),
                        1.0f to Color(0xFF00E5FF),
                        startY = 0f,
                        endY = Float.POSITIVE_INFINITY
                    )
                )
                .padding(padding)
        ) {
            Column(
                Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Card(
                    Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.85f)),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        var name     by rememberSaveable { mutableStateOf("") }
                        var breed    by rememberSaveable { mutableStateOf("") }
                        var sizeText by rememberSaveable { mutableStateOf("") }
                        var ageText  by rememberSaveable { mutableStateOf("") }
                        var photoUrl by rememberSaveable { mutableStateOf("") }

                        OutlinedTextField(
                            value = name,
                            onValueChange = { name = it },
                            label = { Text("Nombre") },
                            leadingIcon = { Icon(Icons.Filled.Pets, contentDescription = null) },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp)
                        )

                        OutlinedTextField(
                            value = breed,
                            onValueChange = { breed = it },
                            label = { Text("Raza") },
                            leadingIcon = { Icon(Icons.Filled.Pets, contentDescription = null) },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp)
                        )

                        OutlinedTextField(
                            value = sizeText,
                            onValueChange = { sizeText = it },
                            label = { Text("Tamaño") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp)
                        )

                        OutlinedTextField(
                            value = ageText,
                            onValueChange = { ageText = it },
                            label = { Text("Edad") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp)
                        )

                        OutlinedTextField(
                            value = photoUrl,
                            onValueChange = { photoUrl = it },
                            label = { Text("URL de Foto") },
                            leadingIcon = { Icon(Icons.Filled.Photo, contentDescription = null) },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp)
                        )

                        Spacer(Modifier.height(8.dp))

                        Button(
                            onClick = {
                                val age = ageText.toIntOrNull() ?: 0
                                onRegister(name.trim(), breed.trim(), sizeText.trim(), age, photoUrl.trim())
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFFF4081), // rosa vivo
                                contentColor = Color.White
                            )
                        ) {
                            Text("Registrar")
                        }
                    }
                }
            }
        }
    }
}
