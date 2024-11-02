package app.jsapps.retroficpractice.model

import com.google.gson.annotations.SerializedName

data class TrackResponse(
  @SerializedName("items") val tracksResult: List<Track>,
)