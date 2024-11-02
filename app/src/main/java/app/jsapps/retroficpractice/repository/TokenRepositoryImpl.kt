package app.jsapps.retroficpractice.repository

import app.jsapps.retroficpractice.data.TokenApiService
import app.jsapps.retroficpractice.model.TokenResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import java.util.Base64
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val tokenApiService: TokenApiService
): TokenRepository {
    override suspend fun getAccessToken(): Call<TokenResponse> {
        val map = mutableMapOf<String, String>()
        val encoder: Base64.Encoder = Base64.getEncoder()
        val string = "${Utils.clientID}:${Utils.secretID}"
        val encoded = encoder.encodeToString(string.toByteArray())
        println("Encoded Data: $encoded")
        map["Authorization"] = "Basic $encoded"
        return withContext(Dispatchers.IO) {
            tokenApiService.getAccesToken(
                "Basic $encoded",
                "client_credentials"
            )
        }
    }
}