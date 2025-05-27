package com.example.glpiwebview.api

import com.example.glpiwebview.PreferenceManager
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface GlpiApiService {
    @POST("initSession")
    suspend fun login(
        @Header("App-Token") appToken: String = PreferenceManager.APP_TOKEN,
        @Body loginRequest: LoginRequest
    ): Response<SessionResponse>
    
    @GET("Ticket")
    suspend fun getTickets(
        @Header("App-Token") appToken: String = PreferenceManager.APP_TOKEN,
        @Header("Session-Token") sessionToken: String,
        @Query("criteria[0][field]") field: Int = 12, // 12 is the field for user ID
        @Query("criteria[0][searchtype]") searchType: String = "equals",
        @Query("criteria[0][value]") userId: Int,
        @Query("expand_dropdowns") expandDropdowns: Boolean = true,
        @Query("get_hateoas") getHateoas: Boolean = false
    ): Response<List<Ticket>>
    
    companion object {
        fun create(baseUrl: String): GlpiApiService {
            val logger = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            
            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .build()
                
            return Retrofit.Builder()
                .baseUrl("$baseUrl/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GlpiApiService::class.java)
        }
    }
}
