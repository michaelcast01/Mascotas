// app/src/main/java/com/example/carnet/IdentificacionMascotas/ScreenB.kt
package com.example.carnet.IdentificacionMascotas

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.carnet.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenB(
    name: String,
    breed: String,
    size: String,
    age: Int,
    photoUrl: String,
    onBack: () -> Unit
) {
    val realUrl = Uri.decode(photoUrl)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Carnet Mascota", color = Color.White) },
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
                        0.0f to Color(0xFF00E5FF),
                        1.0f to Color(0xFF00BFA6),
                        startY = 0f,
                        endY = Float.POSITIVE_INFINITY
                    )
                )
                .padding(padding)
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    elevation = CardDefaults.cardElevation(12.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.85f))
                ) {
                    Column(
                        Modifier
                            .padding(24.dp)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(realUrl)
                                .crossfade(true)
                                .build(),
                            placeholder = painterResource(id = R.drawable.ic_launcher_background),
                            error       = painterResource(id = R.drawable.ic_launcher_foreground),
                            contentDescription = "Foto mascota",
                            modifier = Modifier
                                .size(200.dp)
                                .align(Alignment.CenterHorizontally),
                            contentScale = ContentScale.Crop
                        )
                        Text("Nombre: $name", style = MaterialTheme.typography.headlineSmall)
                        Text("Raza: $breed",  style = MaterialTheme.typography.bodyLarge)
                        Text("Tamaño: $size", style = MaterialTheme.typography.bodyLarge)
                        Text("Edad: $age años", style = MaterialTheme.typography.bodyLarge)
                    }
                }

                Button(
                    onClick = onBack,
                    modifier = Modifier
                        .size(width = 160.dp, height = 50.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF4081),
                        contentColor = Color.White
                    )
                ) {
                    Text("Volver")
                }
            }
        }
    }
}
