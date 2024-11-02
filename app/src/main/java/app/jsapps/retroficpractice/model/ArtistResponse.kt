package app.jsapps.retroficpractice.model

import com.google.gson.annotations.SerializedName

data class ArtistResponse(
    @SerializedName("items") val artistResult: List<Artist>,
)
