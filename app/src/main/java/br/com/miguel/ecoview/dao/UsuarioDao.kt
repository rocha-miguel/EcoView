package br.com.miguel.ecoview.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.miguel.ecoview.model.Usuario


@Dao
interface UsuarioDao {

    @Insert
    fun salvar(usuario: Usuario): Long

    @Delete
    fun deletar(usuario: Usuario): Int

    @Update
    fun atualizar(usuario: Usuario): Int

    @Query("SELECT * FROM USUARIOS")
    fun getUser(): Usuario?

    @Query("SELECT * FROM USUARIOS WHERE id = :id")
    fun getUserById(id: Int): Usuario?

    @Query("SELECT * FROM USUARIOS WHERE email = :email")
    fun getUserByEmail(email: String): Usuario?

    @Query("SELECT * FROM USUARIOS WHERE email = :email AND senha = :senha")
    fun login(email: String, senha: String): Usuario?




}