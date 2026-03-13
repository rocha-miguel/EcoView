package br.com.miguel.ecoview.navigation

sealed class Destination(val route: String) {

    object Inicial : Destination("inicial")

    object Registrar : Destination("registrar")

    object Principal : Destination("principal")

    object Entrar : Destination("entrar")

    object Historico : Destination("historico")

    object Atualizar : Destination("atualizar")

}




