package br.com.miguel.ecoview.repository

import br.com.miguel.ecoview.model.HistoricoCO2

interface HistoricoCO2Repository {

    fun saveHistorico(historico: HistoricoCO2)

    fun getHistorico(): List<HistoricoCO2>

    fun getHistorico(id: Int): HistoricoCO2?

    fun getHistoricoByUsuarioId(usuarioId: Int): List<HistoricoCO2>

    fun getUltimoHistoricoByUsuarioId(usuarioId: Int): HistoricoCO2?

    fun updateHistorico(historico: HistoricoCO2): Int

    fun deleteHistorico(historico: HistoricoCO2): Int
}