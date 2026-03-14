package br.com.miguel.ecoview.repository

import android.content.Context
import br.com.miguel.ecoview.dao.EcoViewDatabase
import br.com.miguel.ecoview.model.Usuario

class RoomUsuarioRepository(context: Context) : UsuarioRepository {

    private val usuarioDao = EcoViewDatabase.getDatabase(context).usuarioDao()

    override fun saveUser(usuario: Usuario) {
        usuarioDao.salvar(usuario)
    }

    override fun getUser(): Usuario {
        TODO("Not yet implemented")
    }

    override fun getUser(id: Int): Usuario {
        return usuarioDao.getUserById(id) ?: Usuario()
    }

    override fun getUserByEmail(email: String): Usuario? {
        return usuarioDao.getUserByEmail(email)
    }

    override fun login(email: String, senha: String): Boolean {
        val user = usuarioDao.login(email, senha)
        return user != null

    }

    override fun updateUser(usuario: Usuario): Int {
        return usuarioDao.atualizar(usuario)

    }

    override fun deleteUser(usuario: Usuario): Int {
        return usuarioDao.deletar(usuario)
    }

}