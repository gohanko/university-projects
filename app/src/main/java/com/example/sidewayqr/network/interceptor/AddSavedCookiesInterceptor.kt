package com.example.sidewayqr.network.interceptor

import android.util.Log
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.sidewayqr.data.datastore.CookieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class AddSavedCookiesInterceptor(
    cookieRepository: CookieRepository
): Interceptor {
    private val _cookieRepository = cookieRepository

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()
        runBlocking {
            _cookieRepository.cookie.collect { cookie ->
                builder.addHeader("Cookie", cookie)
            }
        }
        return chain.proceed(builder.build())
    }
}