package com.example.carnet.IdentificacionMascotas

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument

data class Mascota(
    val nombre: String,
    val raza: String,
    val tamaño: String,
    val edad: Int,
    val sexo: String,
    val urlFoto: String
)

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val listaMascotas: SnapshotStateList<Mascota> = remember { mutableStateListOf() }

    NavHost(navController, startDestination = "registroCarnet") {
        // Pantalla de registro / edición
        composable(
            "registroCarnet/{indice}/{nombre}/{raza}/{tamaño}/{edad}/{sexo}/{urlFoto}",
            arguments = listOf(
                navArgument("indice")  { type = NavType.IntType },
                navArgument("nombre")  { type = NavType.StringType },
                navArgument("raza")    { type = NavType.StringType },
                navArgument("tamaño")  { type = NavType.StringType },
                navArgument("edad")    { type = NavType.IntType },
                navArgument("sexo")    { type = NavType.StringType },
                navArgument("urlFoto") { type = NavType.StringType }
            )
        ) { back ->
            val args = back.arguments!!
            RegistroMascota(
                nombreInicial = args.getString("nombre")!!,
                razaInicial   = args.getString("raza")!!,
                tamañoInicial = args.getString("tamaño")!!,
                edadInicial   = args.getInt("edad"),
                sexoInicial   = args.getString("sexo")!!,
                urlInicial    = args.getString("urlFoto")!!,
                indiceInicial = args.getInt("indice")
            ) { nombre, raza, tamaño, edad, sexo, urlFoto, indice ->
                if (indice < 0) {
                    listaMascotas += Mascota(nombre, raza, tamaño, edad, sexo, urlFoto)
                } else {
                    listaMascotas[indice] = Mascota(nombre, raza, tamaño, edad, sexo, urlFoto)
                }
                navController.navigate("listaCarnet") {
                    popUpTo("registroCarnet") { inclusive = true }
                }
            }
        }
        // Ruta inicial para registro nuevo
        composable("registroCarnet") {
            RegistroMascota(
                nombreInicial = "",
                razaInicial   = "",
                tamañoInicial = "",
                edadInicial   = 0,
                sexoInicial   = "",
                urlInicial    = "",
                indiceInicial = -1
            ) { nombre, raza, tamaño, edad, sexo, urlFoto, indice ->
                listaMascotas += Mascota(nombre, raza, tamaño, edad, sexo, urlFoto)
                navController.navigate("listaCarnet") {
                    popUpTo("registroCarnet") { inclusive = true }
                }
            }
        }
        // Lista de carnés almacenados
        composable("listaCarnet") {
            ListaCarnet(
                mascotas  = listaMascotas,
                alAgregar = { navController.navigate("registroCarnet") },
                alVer     = { idx ->
                    navController.navigate("detalleCarnet/$idx")
                }
            )
        }
        // Detalle / carnét físico
        composable(
            "detalleCarnet/{idx}",
            arguments = listOf(navArgument("idx") { type = NavType.IntType })
        ) { back ->
            val idx = back.arguments!!.getInt("idx")
            val m   = listaMascotas[idx]
            CarnetMascota(
                nombre = m.nombre,
                raza   = m.raza,
                tamaño= m.tamaño,
                edad   = m.edad,
                sexo   = m.sexo,
                urlFoto= m.urlFoto,
                onBack    = { navController.popBackStack() },
                onEliminar = {
                    listaMascotas.removeAt(idx)
                    navController.popBackStack("listaCarnet", false)
                },
                onEditar  = {
                    // navegar a registro con datos cargados
                    val enc = { s: String -> Uri.encode(s) }
                    navController.navigate(
                        "registroCarnet/$idx/" +
                                "${enc(m.nombre)}/${enc(m.raza)}/${enc(m.tamaño)}/${m.edad}/${enc(m.sexo)}/${enc(m.urlFoto)}"
                    )
                }
            )
        }
    }
}
