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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistroMascota(
    nombreInicial: String,
    razaInicial: String,
    tamañoInicial: String,
    edadInicial: Int,
    sexoInicial: String,
    urlInicial: String,
    indiceInicial: Int,
    onRegistrar: (
        nombre: String,
        raza: String,
        tamaño: String,
        edad: Int,
        sexo: String,
        urlFoto: String,
        indice: Int
    ) -> Unit
) {
    var nombre by rememberSaveable { mutableStateOf(nombreInicial) }
    var raza   by rememberSaveable { mutableStateOf(razaInicial) }
    var tamaño by rememberSaveable { mutableStateOf(tamañoInicial) }
    var edadTxt  by rememberSaveable { mutableStateOf(edadInicial.toString()) }
    var sexo   by rememberSaveable { mutableStateOf(sexoInicial) }
    var urlFoto by rememberSaveable { mutableStateOf(urlInicial) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        if (indiceInicial < 0) "Registrar mascota"
                        else "Editar mascota",
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF00796B)
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
                        listOf(Color(0xFFE0F2F1), Color(0xFFB2DFDB))
                    )
                )
                .padding(padding)
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Card(
                    Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.9f))
                ) {
                    Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        OutlinedTextField(
                            value = nombre,
                            onValueChange = { nombre = it },
                            label = { Text("Nombre") },
                            leadingIcon = { Icon(Icons.Filled.Pets, contentDescription = null, tint = Color(0xFF00796B)) },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp)
                        )
                        OutlinedTextField(
                            value = raza,
                            onValueChange = { raza = it },
                            label = { Text("Raza") },
                            leadingIcon = { Icon(Icons.Filled.Pets, contentDescription = null, tint = Color(0xFF00796B)) },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp)
                        )
                        OutlinedTextField(
                            value = tamaño,
                            onValueChange = { tamaño = it },
                            label = { Text("Tamaño") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp)
                        )
                        OutlinedTextField(
                            value = edadTxt,
                            onValueChange = { edadTxt = it },
                            label = { Text("Edad (años)") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp)
                        )
                        OutlinedTextField(
                            value = sexo,
                            onValueChange = { sexo = it },
                            label = { Text("Sexo") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp)
                        )
                        OutlinedTextField(
                            value = urlFoto,
                            onValueChange = { urlFoto = it },
                            label = { Text("URL de foto") },
                            leadingIcon = { Icon(Icons.Filled.Photo, contentDescription = null, tint = Color(0xFF00796B)) },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp)
                        )

                        Spacer(Modifier.height(8.dp))

                        Button(
                            onClick = {
                                val edad = edadTxt.toIntOrNull() ?: 0
                                onRegistrar(nombre, raza, tamaño, edad, sexo, urlFoto, indiceInicial)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00796B))
                        ) {
                            Text(
                                if (indiceInicial < 0) "Registrar"
                                else "Guardar cambios",
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}
