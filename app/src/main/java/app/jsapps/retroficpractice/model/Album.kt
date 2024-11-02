package app.jsapps.retroficpractice.model

import com.google.gson.annotations.SerializedName

data class Album(
    @SerializedName("album_type") val albumType: String,
    val artists: List<Artist>,
    @SerializedName("external_urls")val spotifyUrl: ExternalUrls,
    val href: String,
    val id: String,
    val popularity: Int,
    val images: List<Image>,
    val name: String,
    @SerializedName("release_date") val releaseDate: String,
    val type: String,
    val uri: String
)