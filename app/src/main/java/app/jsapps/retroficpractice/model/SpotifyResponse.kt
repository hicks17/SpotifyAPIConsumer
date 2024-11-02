package app.jsapps.retroficpractice.model

data class SpotifyResponse(
    val tracks: TrackResponse? = null,
    val artists: ArtistResponse? = null,
    val albums: AlbumResponse? = null,
)

