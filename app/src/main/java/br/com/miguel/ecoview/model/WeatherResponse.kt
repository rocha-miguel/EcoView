package br.com.miguel.ecoview.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("current")
    val current: CurrentWeather,
    @SerializedName("daily")
    val daily: DailyWeather
)

data class CurrentWeather(
    @SerializedName("temperature_2m")
    val temperature: Double,

    @SerializedName("apparent_temperature")
    val apparentTemperature: Double,

    @SerializedName("weather_code")
    val weatherCode: Int,

    @SerializedName("cloud_cover")
    val cloudCover: Int,

    @SerializedName("is_day")
    val isDay: Int
)

data class DailyWeather(
    @SerializedName("temperature_2m_max")
    val temperatureMax: List<Double>,
    @SerializedName("temperature_2m_min")
    val temperatureMin: List<Double>
)