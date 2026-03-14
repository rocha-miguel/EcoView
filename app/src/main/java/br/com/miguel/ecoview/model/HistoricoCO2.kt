package br.com.miguel.ecoview.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "historico_co2")
data class HistoricoCO2(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "usuario_id")
    val usuarioId: Int,
    @ColumnInfo(name = "km_rodados")
    val kmRodados: Double,
    val alimentacao: String,
    @ColumnInfo(name = "consumo_energia")
    val consumoEnergia: Double,
    @ColumnInfo(name = "resultado_co2")
    val resultadoCo2: Double,

)
