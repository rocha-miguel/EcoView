package br.com.miguel.ecoview.service

import br.com.miguel.ecoview.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("v1/forecast")
    suspend fun getWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("current") current: String = "temperature_2m,apparent_temperature,weather_code,cloud_cover,is_day",
        @Query("daily") daily: String = "temperature_2m_max,temperature_2m_min",
        @Query("timezone") timezone: String = "auto"
    ): WeatherResponse
}