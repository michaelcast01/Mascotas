package com.example.carnet.IdentificacionMascotas

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.carnet.IdentificacionMascotas.ScreenA
import com.example.carnet.IdentificacionMascotas.ScreenB

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "screenA") {
        composable("screenA") {
            ScreenA { name, breed, size, age, photoUrl ->
                val route = listOf(name, breed, size, age.toString(), Uri.encode(photoUrl))
                    .joinToString("/") { Uri.encode(it) }
                navController.navigate("screenB/$route")
            }
        }
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
