package com.example.sidewayqr.network

import com.example.sidewayqr.data.api.authentication.RegisterLoginRequest
import com.example.sidewayqr.data.api.GenericAPIResponse
import com.example.sidewayqr.data.api.authentication.ChangePasswordRequest
import com.example.sidewayqr.data.api.authentication.LoginResponse
import com.example.sidewayqr.data.api.event.AttendEventRequest
import com.example.sidewayqr.data.api.event.CreateUpdateEventRequest
import com.example.sidewayqr.data.api.event.GetEventResponse
import com.example.sidewayqr.data.datastore.CookieRepository
import com.example.sidewayqr.network.interceptor.AddSavedCookiesInterceptor
import com.example.sidewayqr.network.interceptor.SaveReceivedCookiesInterceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

const val BASE_URL = "https://sidewayqr-api.azurewebsites.net/"

interface SidewayQRAPIService {
    companion object {
        private var apiService: SidewayQRAPIService? = null
        fun getInstance(cookieRepository: CookieRepository): SidewayQRAPIService {
            if (apiService == null) {
                val okHttpClient: OkHttpClient = OkHttpClient()
                    .newBuilder()
                    .addInterceptor(SaveReceivedCookiesInterceptor(cookieRepository))
                    .addInterceptor(AddSavedCookiesInterceptor(cookieRepository))
                    .build()

                apiService = Retrofit.Builder()
                    .client(okHttpClient)
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
    fun register(@Body request: RegisterLoginRequest): Call<GenericAPIResponse>

    @POST("/api/user/login/")
    fun login(@Body request: RegisterLoginRequest): Call<LoginResponse>

    @GET("/api/user/logout/")
    fun logout(): Call<GenericAPIResponse>

    @GET("/api/user/change_password/")
    fun changePassword(@Body request: ChangePasswordRequest): Call<GenericAPIResponse>

    // Event
    @POST("/api/event/")
    fun createEvent()

    @GET("/api/event/{eventId}")
    fun readEvent(@Path("eventId") eventId: Int): Call<GenericAPIResponse>

    @PUT("/api/event/{eventId}")
    fun updateEvent(
        @Path("eventId") eventId: Int,
        @Body request: CreateUpdateEventRequest
    ): Call<GenericAPIResponse>

    @DELETE("/api/event/{eventId}")
    fun deleteEvent(@Path("eventId") eventId: Int): Call<GenericAPIResponse>

    @GET("/api/event/{eventId}/code")
    fun getEventCode(@Path("eventId") eventId: Int): Call<GenericAPIResponse>

    @GET("/api/event/{eventId}/join")
    fun joinEvent(@Path("eventId") eventId: Int): Call<GenericAPIResponse>

    @GET("/api/event/{eventId}/leave")
    fun leaveEvent(@Path("eventId") eventId: Int): Call<GenericAPIResponse>

    @PUT("/api/event/{eventId}/attend")
    fun attendEvent(
        @Path("eventId") eventId: Int,
        @Body request: AttendEventRequest
    ): Call<GenericAPIResponse>

    @GET("/api/events/")
    fun getEvents(): Call<GetEventResponse>
}