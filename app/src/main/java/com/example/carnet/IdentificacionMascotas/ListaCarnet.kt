package com.example.carnet.IdentificacionMascotas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaCarnet(
    mascotas: List<Mascota>,
    alAgregar: () -> Unit,
    alVer: (Int) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Mis carnets almacenados", color = Color(0xFF004D40)) })
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                icon = { Icon(Icons.Default.Add, contentDescription = "Añadir") },
                text = { Text("Añadir mascota") },
                onClick = alAgregar,
                containerColor = Color(0xFF00796B),
                contentColor   = Color.White
            )
        }
    ) { padding ->
        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            itemsIndexed(mascotas) { idx, m ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { alVer(idx) },
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {
                    Row(Modifier.padding(16.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(m.urlFoto)
                                .crossfade(true)
                                .build(),
                            contentDescription = m.nombre,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(64.dp)
                                .clip(RoundedCornerShape(8.dp))
                        )
                        Column {
                            Text(m.nombre, style = MaterialTheme.typography.titleMedium)
                            Text(m.raza,    style = MaterialTheme.typography.bodyMedium)
                            Text("${m.edad} años", style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }
        }
    }
}
