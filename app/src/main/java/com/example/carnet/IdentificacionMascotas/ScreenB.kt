package com.example.carnet.IdentificacionMascotas

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.carnet.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarnetMascota(
    nombre: String,
    raza: String,
    tamaño: String,
    edad: Int,
    sexo: String,
    urlFoto: String,
    onBack: () -> Unit,
    onEliminar: () -> Unit,
    onEditar: () -> Unit
) {
    var mostrarDialogo by remember { mutableStateOf(false) }
    val fotoReal = Uri.decode(urlFoto)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Carné de Mascota", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Volver", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF004D40))
            )
        },
        containerColor = Color(0xFFE0F2F1)
    ) { padding ->
        Column(
            Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(Color(0xFFB2DFDB), Color(0xFFE0F2F1))
                    )
                )
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Card(
                Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(10.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(fotoReal)
                            .crossfade(true)
                            .build(),
                        placeholder = painterResource(R.drawable.ic_launcher_background),
                        error       = painterResource(R.drawable.ic_launcher_foreground),
                        contentDescription = "Foto mascota",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                            .clip(RoundedCornerShape(12.dp)),
                        contentScale = ContentScale.Crop
                    )

                    Text("Nombre:", style = MaterialTheme.typography.labelSmall)
                    Text(nombre,   style = MaterialTheme.typography.titleLarge)

                    Text("Raza:", style = MaterialTheme.typography.labelSmall)
                    Text(raza,    style = MaterialTheme.typography.bodyLarge)

                    Text("Tamaño:", style = MaterialTheme.typography.labelSmall)
                    Text(tamaño,   style = MaterialTheme.typography.bodyLarge)

                    Text("Edad:", style = MaterialTheme.typography.labelSmall)
                    Text("$edad años", style = MaterialTheme.typography.bodyLarge)

                    Text("Sexo:", style = MaterialTheme.typography.labelSmall)
                    Text(sexo,    style = MaterialTheme.typography.bodyLarge)

                    Text("URL de foto:", style = MaterialTheme.typography.labelSmall)
                    Text(urlFoto,        style = MaterialTheme.typography.bodySmall)

                    Spacer(Modifier.height(8.dp))

                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(onClick = onEditar) {
                            Icon(Icons.Filled.Edit, contentDescription = "Editar", tint = Color(0xFF004D40))
                            Spacer(Modifier.width(4.dp))
                            Text("Editar", color = Color(0xFF004D40))
                        }
                        Spacer(Modifier.width(16.dp))
                        TextButton(
                            onClick = { mostrarDialogo = true },
                            colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.error)
                        ) {
                            Icon(Icons.Filled.Close, contentDescription = "Eliminar")
                            Spacer(Modifier.width(4.dp))
                            Text("Eliminar")
                        }
                    }
                }
            }

            if (mostrarDialogo) {
                AlertDialog(
                    onDismissRequest = { mostrarDialogo = false },
                    title = { Text("Confirmar eliminación") },
                    text  = { Text("¿Eliminar el carné de “$nombre”?") },
                    confirmButton = {
                        TextButton(onClick = {
                            mostrarDialogo = false
                            onEliminar()
                        }) { Text("Sí") }
                    },
                    dismissButton = {
                        TextButton(onClick = { mostrarDialogo = false }) { Text("No") }
                    }
                )
            }
        }
    }
}
