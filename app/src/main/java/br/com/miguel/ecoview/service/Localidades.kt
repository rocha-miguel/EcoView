package br.com.miguel.ecoview.service

import br.com.miguel.ecoview.model.CidadeInfo

object Localidades {
    val estadosECidades = mapOf(
        "Rio de Janeiro" to listOf(
            CidadeInfo("Rio de Janeiro", -22.9068, -43.1729),
            CidadeInfo("Niterói", -22.8832, -43.1034),
            CidadeInfo("São Gonçalo", -22.8268, -43.0634),
            CidadeInfo("Magé", -22.6556, -43.0402)
        ),
        "São Paulo" to listOf(
            CidadeInfo("São Paulo", -23.5505, -46.6333),
            CidadeInfo("Campinas", -22.9099, -47.0626),
            CidadeInfo("Santos", -23.9608, -46.3336),
            CidadeInfo("Sorocaba", -23.5015, -47.4526)
        ),
        "Minas Gerais" to listOf(
            CidadeInfo("Belo Horizonte", -19.9167, -43.9345),
            CidadeInfo("Uberlândia", -18.9186, -48.2772),
            CidadeInfo("Contagem", -19.9321, -44.0539),
            CidadeInfo("Juiz de Fora", -21.7642, -43.3503)
        )
    )

    fun buscarCoordenadas(estado: String, cidade: String): CidadeInfo? {
        return estadosECidades[estado]?.firstOrNull {
            it.nome.equals(cidade, ignoreCase = true)
        }
    }
}