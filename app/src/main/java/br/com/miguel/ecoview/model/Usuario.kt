package br.com.miguel.ecoview.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "USUARIOS",
    indices = [Index(value = ["email"], unique = true)]
)
data class Usuario(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nome: String = "",
    val email: String = "",
    val senha: String = "",
    val estado: String = "",
    val cidade: String = "",
    @ColumnInfo(name = "imagem_usuario")
    val imagemUsuario: ByteArray? = null
)