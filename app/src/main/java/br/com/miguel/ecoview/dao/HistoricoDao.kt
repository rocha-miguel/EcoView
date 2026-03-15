package br.com.miguel.ecoview.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.miguel.ecoview.model.HistoricoCO2

@Dao
interface HistoricoCo2Dao {

    @Insert
    fun salvar(historico: HistoricoCO2): Long

    @Delete
    fun deletar(historico: HistoricoCO2): Int

    @Update
    fun atualizar(historico: HistoricoCO2): Int

    @Query("SELECT * FROM historico_co2")
    fun getHistorico(): List<HistoricoCO2>

    @Query("SELECT * FROM historico_co2 WHERE id = :id")
    fun getHistoricoById(id: Int): HistoricoCO2?

    @Query("SELECT * FROM historico_co2 WHERE usuario_id = :usuarioId ORDER BY id DESC")
    fun getHistoricoByUsuarioId(usuarioId: Int): List<HistoricoCO2>

    @Query("SELECT * FROM historico_co2 WHERE usuario_id = :usuarioId ORDER BY id DESC")
    fun getUltimoHistoricoByUsuarioId(usuarioId: Int): HistoricoCO2?
}