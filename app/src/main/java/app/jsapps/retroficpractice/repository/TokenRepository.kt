package app.jsapps.retroficpractice.repository

import app.jsapps.retroficpractice.model.TokenResponse
import retrofit2.Call

interface TokenRepository {

    suspend fun getAccessToken(): Call<TokenResponse>
}