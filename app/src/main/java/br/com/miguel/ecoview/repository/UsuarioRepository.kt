package br.com.miguel.ecoview.repository


import br.com.miguel.ecoview.model.Usuario

interface UsuarioRepository {

    fun saveUser(usuario: Usuario)

    fun getUser(): Usuario

    fun getUser(id: Int): Usuario?

    fun getUserByEmail(email: String): Usuario?

    fun login(email: String, senha: String): Boolean

    fun updateUser(usuario: Usuario): Int

    fun deleteUser(usuario: Usuario): Int


}