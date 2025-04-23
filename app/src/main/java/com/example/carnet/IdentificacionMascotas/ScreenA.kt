package com.example.carnet.IdentificacionMascotas


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ScreenA(
    onRegister: (name: String, breed: String, size: String, age: Int, photoUrl: String) -> Unit
) {
    var name by rememberSaveable { mutableStateOf("") }
    var breed by rememberSaveable { mutableStateOf("") }
    var size by rememberSaveable { mutableStateOf("") }
    var ageText by rememberSaveable { mutableStateOf("") }
    var photoUrl by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        TextField(value = name, onValueChange = { name = it }, label = { Text("Nombre") })
        Spacer(Modifier.height(8.dp))
        TextField(value = breed, onValueChange = { breed = it }, label = { Text("Raza") })
        Spacer(Modifier.height(8.dp))
        TextField(value = size, onValueChange = { size = it }, label = { Text("Tamaño") })
        Spacer(Modifier.height(8.dp))
        TextField(value = ageText, onValueChange = { ageText = it }, label = { Text("Edad") })
        Spacer(Modifier.height(8.dp))
        TextField(value = photoUrl, onValueChange = { photoUrl = it }, label = { Text("Foto URL") })
        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {
                val age = ageText.toIntOrNull() ?: 0
                onRegister(name, breed, size, age, photoUrl)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrar")
        }
    }
}
