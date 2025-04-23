// app/src/main/java/com/example/carnet/IdentificacionMascotas/Navigation.kt
package com.example.carnet.IdentificacionMascotas

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "screenA") {
        // 1) Pantalla A: formulario
        composable("screenA") {
            ScreenA { name, breed, size, age, photoUrl ->
                // codificamos cada parte SÓLO UNA VEZ
                val encodedName     = Uri.encode(name)
                val encodedBreed    = Uri.encode(breed)
                val encodedSize     = Uri.encode(size)
                val encodedPhotoUrl = Uri.encode(photoUrl)
                // construimos la ruta
                val route = "screenB/$encodedName/$encodedBreed/$encodedSize/$age/$encodedPhotoUrl"
                navController.navigate(route)
            }
        }
        // 2) Pantalla B: detalle
        composable(
            "screenB/{name}/{breed}/{size}/{age}/{photoUrl}",
            arguments = listOf(
                navArgument("name")     { type = NavType.StringType },
                navArgument("breed")    { type = NavType.StringType },
                navArgument("size")     { type = NavType.StringType },
                navArgument("age")      { type = NavType.IntType    },
                navArgument("photoUrl") { type = NavType.StringType }
            )
        ) { backStack ->
            val args = backStack.arguments!!
            ScreenB(
                name     = args.getString("name")!!,
                breed    = args.getString("breed")!!,
                size     = args.getString("size")!!,
                age      = args.getInt("age"),
                photoUrl = args.getString("photoUrl")!!,
                onBack   = { navController.popBackStack() }
            )
        }
    }
}
