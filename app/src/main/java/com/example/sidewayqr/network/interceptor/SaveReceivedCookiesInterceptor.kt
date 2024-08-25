package com.example.sidewayqr.network.interceptor

import android.util.Log
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.sidewayqr.data.datastore.CookieRepository
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class SaveReceivedCookiesInterceptor(
    cookieRepository: CookieRepository
): Interceptor {
    private val _cookieRepository = cookieRepository

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse: Response = chain.proceed(chain.request())
        if (originalResponse.headers("Set-Cookie").isNotEmpty()) {
            val cookies: HashSet<String> = HashSet()
            for (header in originalResponse.headers("Set-Cookie")) {
                cookies.add(header)
            }

            runBlocking {
                _cookieRepository.saveCookie(cookies.toString())
            }
        }

        return originalResponse
    }
}