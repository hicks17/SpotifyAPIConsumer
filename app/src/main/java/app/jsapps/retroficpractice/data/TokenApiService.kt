package app.jsapps.retroficpractice.data

import app.jsapps.retroficpractice.model.TokenResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.HeaderMap
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface TokenApiService {

    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("/api/token")
    fun getAccesToken(
        @Header("Authorization") auth:String,
        @Field(("grant_type")) grantType:String ): Call<TokenResponse>
}