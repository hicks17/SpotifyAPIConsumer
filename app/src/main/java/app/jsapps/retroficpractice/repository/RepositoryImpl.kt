package app.jsapps.retroficpractice.repository

import android.content.ContentValues.TAG
import android.util.Log
import app.jsapps.retroficpractice.data.SpotifyApiService
import app.jsapps.retroficpractice.model.SpotifyResponse
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val spotifyApiService: SpotifyApiService) : NetworkRepository{

    override suspend fun getTracksByName(input: String, tokenAccess:String, types:List<String>): SpotifyResponse? {

        val header = mutableMapOf<String, String>()
        header["Authorization"] = "Bearer $tokenAccess"
        Log.w(TAG, "token: $tokenAccess")
        runCatching { spotifyApiService.getTracksByName(input, types, 15, header) }
            .onSuccess { return it }
            .onFailure { Log.w(TAG, "No se ha podido completar la acción ${it.message}") }

        return null
    }
}