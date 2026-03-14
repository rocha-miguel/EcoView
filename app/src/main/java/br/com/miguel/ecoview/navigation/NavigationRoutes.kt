package br.com.miguel.ecoview.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.miguel.ecoview.screens.TelaAtualizar
import br.com.miguel.ecoview.screens.TelaEntrar
import br.com.miguel.ecoview.screens.TelaHistorico
import br.com.miguel.ecoview.screens.TelaInicial
import br.com.miguel.ecoview.screens.TelaPrincipal
import br.com.miguel.ecoview.screens.TelaRegistrar

@Composable
fun NavigationRoutes() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Destination.Inicial.route,
        modifier = Modifier
    ) {
        composable(route = Destination.Inicial.route) {
            TelaInicial(navController = navController)
        }

        composable(route = Destination.Entrar.route) {
            TelaEntrar(navController = navController)
        }

        composable(route = Destination.Registrar.route) {
            TelaRegistrar(navController = navController)
        }

        composable(
            Destination.Principal.route,
            arguments = listOf(navArgument("email") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            var email = backStackEntry.arguments?.getString("email")
            TelaPrincipal(navController, email)
        }

        composable(
            Destination.Historico.route,
            arguments = listOf(navArgument("email") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            var email = backStackEntry.arguments?.getString("email")
            TelaHistorico(navController, email)
        }

        composable(
            Destination.Atualizar.route,
            arguments = listOf(navArgument("email") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            var email = backStackEntry.arguments?.getString("email")
            TelaAtualizar(navController, email)
        }

    }
}