package com.example.carnet.IdentificacionMascotas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Photo
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenA(
    onRegister: (name: String, breed: String, size: String, age: Int, photoUrl: String) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Registro Mascota") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            var name     by rememberSaveable { mutableStateOf("") }
            var breed    by rememberSaveable { mutableStateOf("") }
            var size     by rememberSaveable { mutableStateOf("") }
            var ageText  by rememberSaveable { mutableStateOf("") }
            var photoUrl by rememberSaveable { mutableStateOf("") }

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nombre") },
                placeholder = { Text("Ej. Firulais") },
                leadingIcon = { Icon(Icons.Filled.Pets, contentDescription = null) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = breed,
                onValueChange = { breed = it },
                label = { Text("Raza") },
                placeholder = { Text("Ej. Bulldog") },
                leadingIcon = { Icon(Icons.Filled.Pets, contentDescription = null) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = size,
                onValueChange = { size = it },
                label = { Text("Tamaño") },
                placeholder = { Text("Pequeño / Mediano / Grande") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = ageText,
                onValueChange = { ageText = it },
                label = { Text("Edad") },
                placeholder = { Text("En años") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = photoUrl,
                onValueChange = { photoUrl = it },
                label = { Text("URL de Foto") },
                placeholder = { Text("https://...") },
                leadingIcon = { Icon(Icons.Filled.Photo, contentDescription = null) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            Button(
                onClick = {
                    val age = ageText.toIntOrNull() ?: 0
                    onRegister(
                        name.trim(),
                        breed.trim(),
                        size.trim(),
                        age,
                        photoUrl.trim()
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium
            ) {
                Text("Registrar")
            }
        }
    }
}
