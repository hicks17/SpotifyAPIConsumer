package app.jsapps.retroficpractice.ext

import android.content.ContentValues.TAG
import android.util.Log
import app.jsapps.retroficpractice.model.Album
import app.jsapps.retroficpractice.model.Artist
import app.jsapps.retroficpractice.model.ArtistModel
import app.jsapps.retroficpractice.model.Image
import app.jsapps.retroficpractice.model.ResponseModel
import app.jsapps.retroficpractice.model.SpotifyResponse
import app.jsapps.retroficpractice.model.Track
import app.jsapps.retroficpractice.model.TrackModel

fun Track.toDomain(): TrackModel {
    return TrackModel(
        name = name,
        artist = artists.map { it.toDomain() },
        duration = durationMs,
        albumUrl = album.images[0].url

    )
}

fun Artist.toDomain(): ArtistModel {
    return ArtistModel(
        name = name,
        id = id,
        href = href,
        sref = externalUrls.spotify
    )
}

fun SpotifyResponse.toAllModel():List<ResponseModel>{
    var list = mutableListOf<ResponseModel>()
    if(this.albums != null){
        list.addAll(this.albums.albumsResult.map {
            it.toAllModel()
        })
    }
    if(this.artists != null){
        list.addAll(this.artists.artistResult.map {
            it.toAllModel()
        })
    }
    if(this.tracks != null){
        list.addAll(this.tracks.tracksResult.map {
            it.toAllModel()
        })
    }

    Log.w(TAG, "result: $list")
    return list
}

fun Album.toAllModel():ResponseModel{
    return ResponseModel(
        albumName = name,
        artistsName = artists,
        type = "album",
        popularity = popularity,
        spotifyRef = spotifyUrl.spotify,
        imageUrl = images[0].url
    )
}
fun Artist.toAllModel():ResponseModel{
    return if(this.images.isNotEmpty()) {

        Log.w(TAG ,"images: $images")
        ResponseModel(
            artistName = name,
            type = "artist",
            popularity = popularity,
            spotifyRef = externalUrls.spotify,
            imageUrl = images[0].url
        )
    }else{
        ResponseModel(
            artistName = name,
            type = "artist",
            popularity = popularity,
            spotifyRef = externalUrls.spotify,
            imageUrl = "https://i.scdn.co/image/ab6761610000e5eb55d39ab9c21d506aa52f7021"
        )
    }
}
fun Track.toAllModel():ResponseModel{
    return ResponseModel(
        trackName = name,
        albumName = album.name,
        artistsName = artists,
        type = "track",
        popularity = popularity,
        spotifyRef = externalUrls.spotify,
        imageUrl = album.images[0].url
    )
}