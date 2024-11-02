package app.jsapps.retroficpractice.model

import com.google.gson.annotations.SerializedName

data class Track(
    @SerializedName("album") val album: Album,
    @SerializedName("artists") val artists: List<Artist>,
    @SerializedName("external_urls") val externalUrls: ExternalUrls,
    @SerializedName("duration_ms") val durationMs: Int,
    val explicit: Boolean,
    val href: String,
    val name: String,
    val popularity: Int,
    @SerializedName("preview_url") val previewUrl: String,
    val type: String,
    val uri: String
)

