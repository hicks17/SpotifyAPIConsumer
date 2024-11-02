package app.jsapps.retroficpractice.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.jsapps.retroficpractice.domain.GetAllUseCase
import app.jsapps.retroficpractice.domain.GetSongsByNameUseCase
import app.jsapps.retroficpractice.model.ResponseModel
import app.jsapps.retroficpractice.model.TrackModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrackViewModel @Inject constructor(
    private val getSongsByNameUseCase: GetSongsByNameUseCase,
    private val getAllUseCase: GetAllUseCase,
) : ViewModel() {

    private val _songListResult:MutableStateFlow<List<TrackModel>> = MutableStateFlow(emptyList())
    val songListResult = _songListResult.asStateFlow()

    private val _responseResult:MutableStateFlow<List<ResponseModel>> = MutableStateFlow(emptyList())
    val responseResult = _responseResult.asStateFlow()
    private val _Result:MutableStateFlow<List<ResponseModel>> = MutableStateFlow(emptyList())
    val result = _Result.asStateFlow()

    fun searchSongsByName(input: String){
        viewModelScope.launch {

            _responseResult.update {
                result.value.filter { it.type == "track" }
            }
        }
    }

    fun resetResult(){
        viewModelScope.launch {
            _responseResult.update {
                result.value
            }
        }
    }
    fun searchArtistsByName(input: String){


        viewModelScope.launch {

            _responseResult.update {
                result.value.filter { it.type == "artist" }

            }
        }
    }
    fun searchAlbumsByName(input: String){


        viewModelScope.launch {

            _responseResult.update {
                result.value.filter { it.type == "album" }
            }
        }
    }

    fun getItemsByQuery(input: String){
        viewModelScope.launch {
            val query = getAllUseCase.invoke(input, listOf("track,artist,album")).shuffled().sortedByDescending { it.popularity }
            query.forEach {
                Log.w(TAG, "${it.type} ${it.popularity}")
            }
            _responseResult.update {
                query
            }
            _Result.update {
                query
            }
        }
    }
}