package app.jsapps.retroficpractice.model

import com.google.gson.annotations.SerializedName

data class AlbumResponse(
    @SerializedName("items") val albumsResult: List<Album>,
)
