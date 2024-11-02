package app.jsapps.retroficpractice.data

import app.jsapps.retroficpractice.model.SpotifyResponse
import com.google.gson.annotations.SerializedName
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.HeaderMap
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface SpotifyApiService {


    @GET("search/")
    suspend fun getTracksByName(
        @Query("q") input:String,
        @Query("type") type:List<String>,
        @Query("limit") limit:Int,
        @HeaderMap auth:Map<String, String>,

    ):SpotifyResponse
}