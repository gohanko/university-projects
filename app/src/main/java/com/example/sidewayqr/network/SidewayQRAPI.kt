package com.example.sidewayqr.network

import com.example.sidewayqr.data.Event
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface SidewayQRAPIService {
    companion object {
        private var apiService: SidewayQRAPIService? = null
        fun getInstance(): SidewayQRAPIService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(SidewayQRAPIService::class.java)
            }
            return apiService!!
        }

        private const val BASE_URL = "https://sidewayqr-api-gyd5bsfrd4ehfvec.southeastasia-01.azurewebsites.net/"
    }

    @GET("/api/events/")
    suspend fun getEvents(): List<Event>
}