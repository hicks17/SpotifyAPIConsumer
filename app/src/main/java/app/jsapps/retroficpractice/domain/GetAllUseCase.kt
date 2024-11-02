package app.jsapps.retroficpractice.domain

import android.util.Log
import app.jsapps.retroficpractice.ext.toAllModel
import app.jsapps.retroficpractice.model.ResponseModel
import app.jsapps.retroficpractice.repository.NetworkRepository
import app.jsapps.retroficpractice.repository.TokenRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class GetAllUseCase @Inject constructor(
    private val repository: NetworkRepository,
    private val tokenRepository: TokenRepository
    ) {

    suspend operator fun invoke(input:String, list: List<String>):List<ResponseModel> {
        return repository.getTracksByName(input, getAccessToken(), list)!!.toAllModel()
    }

    private suspend fun getAccessToken():String{

        return withContext(Dispatchers.IO){
            tokenRepository.getAccessToken().execute().body()?.accessToken ?: "hola"
        }
    }

}