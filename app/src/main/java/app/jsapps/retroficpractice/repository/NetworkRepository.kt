package app.jsapps.retroficpractice.repository

import app.jsapps.retroficpractice.model.SpotifyResponse

interface NetworkRepository {

    suspend fun getTracksByName(input:String, tokenAccess:String, types:List<String>):SpotifyResponse?

}