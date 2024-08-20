package com.example.sidewayqr.network

import com.example.sidewayqr.data.api.AuthenticationRequest
import com.example.sidewayqr.data.api.GenericAPIResponse
import com.example.sidewayqr.data.model.Event
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

const val BASE_URL = "http://192.168.0.138:3000/"

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
    }

    // Register, Login, and Logout
    @POST("/api/user/register/")
    fun register(@Body request: AuthenticationRequest): Call<GenericAPIResponse>

    @POST("/api/user/login/")
    fun login(@Body request: AuthenticationRequest): Call<GenericAPIResponse>

    @GET("/api/user/logout/")
    fun logout(@Body request: AuthenticationRequest): Call<GenericAPIResponse>

    @GET("/api/user/change_password/")
    fun changePassword()

    // Event
    @GET("/api/events/")
    suspend fun getEvents(): List<Event>

    @POST("/api/events/")
    suspend fun createEvent()

    @GET("/api/events/{eventId}")
    suspend fun getEvent(@Path("eventId") eventId: Int)

    @PUT("/api/events/{eventId}")
    suspend fun updateEvent(@Path("eventId") eventId: Int)

    @DELETE("/api/events/{eventId}")
    suspend fun deleteEvent(@Path("eventId") eventId: Int)

    // Event Participation
    @GET("/api/event/participation/")
    suspend fun getEventParticipations()

    @POST("/api/event/participation/{eventId}/join")
    suspend fun joinEvent(@Path("eventId") eventId: Int)

    @DELETE("/api/event/participation/{eventId}/leave")
    suspend fun leaveEvent(@Path("eventId") eventId: Int)

    @GET("/api/event/{eventId}/getCode/")
    suspend fun getEventCode(@Path("eventId") eventId: Int)

    @POST("/api/event/{eventId}/attend/")
    suspend fun attendEvent(@Path("eventId") eventId: Int)
}