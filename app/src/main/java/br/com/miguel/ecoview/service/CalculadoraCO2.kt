package br.com.miguel.ecoview.service

object CalculadoraCO2 {

    fun calcular(
        kmRodados: Double,
        alimentacao: String,
        consumoEnergia: Double
    ): Double {
        val emissaoTransporte = kmRodados * 0.21

        val emissaoAlimentacao = when (alimentacao.lowercase()) {
            "muita carne" -> 3.3
            "pouca carne" -> 2.5
            "vegetariana" -> 1.7
            "vegana" -> 1.5
            else -> 0.0
        }

        val emissaoEnergia = consumoEnergia * 0.08

        return emissaoTransporte + emissaoAlimentacao + emissaoEnergia
    }
}