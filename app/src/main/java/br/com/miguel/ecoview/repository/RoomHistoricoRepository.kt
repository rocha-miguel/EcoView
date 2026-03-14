package br.com.miguel.ecoview.repository

import android.content.Context
import br.com.miguel.ecoview.dao.EcoViewDatabase
import br.com.miguel.ecoview.model.HistoricoCO2

class RoomHistoricoCO2Repository(context: Context) : HistoricoCO2Repository {

    private val historicoCo2Dao = EcoViewDatabase.getDatabase(context).historicoCo2Dao()

    override fun saveHistorico(historico: HistoricoCO2) {
        historicoCo2Dao.salvar(historico)
    }

    override fun getHistorico(): List<HistoricoCO2> {
        return historicoCo2Dao.getHistorico()
    }

    override fun getHistorico(id: Int): HistoricoCO2? {
        return historicoCo2Dao.getHistoricoById(id)
    }

    override fun getHistoricoByUsuarioId(usuarioId: Int): List<HistoricoCO2> {
        return historicoCo2Dao.getHistoricoByUsuarioId(usuarioId)
    }

    override fun getUltimoHistoricoByUsuarioId(usuarioId: Int): HistoricoCO2? {
        return historicoCo2Dao.getUltimoHistoricoByUsuarioId(usuarioId)
    }

    override fun updateHistorico(historico: HistoricoCO2): Int {
        return historicoCo2Dao.atualizar(historico)
    }

    override fun deleteHistorico(historico: HistoricoCO2): Int {
        return historicoCo2Dao.deletar(historico)
    }
}