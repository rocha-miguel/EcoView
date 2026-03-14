package br.com.miguel.ecoview.navigation

sealed class Destination(val route: String) {

    object Inicial : Destination("inicial")

    object Registrar : Destination("registrar")

     object Principal : Destination("principal/{email}") {
         fun createRoute(email: String): String {
             return "principal/$email"
         }
    }

    object Entrar : Destination("entrar")

    object Historico : Destination("historico/{email}") {
        fun createRoute(email: String): String {
            return "historico/$email"
        }
    }

    object Atualizar : Destination("atualizar/{email}") {
        fun createRoute(email: String): String {
            return "atualizar/$email"
        }
    }

}




