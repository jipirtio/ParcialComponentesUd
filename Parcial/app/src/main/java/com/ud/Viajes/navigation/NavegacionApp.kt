package com.ud.Viajes.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ud.Viajes.views.DetallesViajeVista
import com.ud.Viajes.views.ListaViajesVista
import com.ud.Viajes.views.NuevoViajeVista

/**
 * Función Composable que define la navegación de la aplicación.
 */
@Composable
fun NavegacionApp() {
    val controladorNavegacion = rememberNavController()
    NavHost(navController = controladorNavegacion, startDestination = "listaViajes") {
        composable("listaViajes") {
            ListaViajesVista(controladorNavegacion)
        }
        composable(
            "detallesViaje/{idViaje}",
            arguments = listOf(navArgument("idViaje") { type = NavType.IntType })
        ) { backStackEntry ->
            val idViaje = backStackEntry.arguments?.getInt("idViaje")
            idViaje?.let { DetallesViajeVista(controladorNavegacion, it) }
        }
        composable(
            "nuevoViaje/{idViaje}",
            arguments = listOf(navArgument("idViaje") { type = NavType.IntType })
        ) { backStackEntry ->
            val idViaje = backStackEntry.arguments?.getInt("idViaje")
            idViaje?.let { NuevoViajeVista(controladorNavegacion, it) }
        }
    }
}