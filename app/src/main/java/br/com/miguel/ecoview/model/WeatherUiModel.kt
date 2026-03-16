package br.com.miguel.ecoview.model

data class WeatherUiModel(
    val cityName: String,
    val temperature: Int,
    val maxTemperature: Int,
    val minTemperature: Int,
    val condition: String,
    val supportMessage: String,
    val imageRes: Int
)