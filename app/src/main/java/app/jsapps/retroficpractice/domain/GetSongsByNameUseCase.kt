package app.jsapps.retroficpractice.domain

import app.jsapps.retroficpractice.ext.toDomain
import app.jsapps.retroficpractice.model.TrackModel
import app.jsapps.retroficpractice.repository.NetworkRepository
import app.jsapps.retroficpractice.repository.TokenRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class GetSongsByNameUseCase @Inject constructor(
    private val repository: NetworkRepository,
    private val tokenRepository: TokenRepository
    ) {

    suspend operator fun invoke(input:String):List<TrackModel> {
        return repository.getTracksByName(input, getAccessToken(), listOf("track"))?.tracks?.tracksResult?.map { it.toDomain() } ?: emptyList()
    }

    private suspend fun getAccessToken():String{

        return withContext(Dispatchers.IO){
            tokenRepository.getAccessToken().execute().body()?.accessToken ?: "hola"
        }
    }

}