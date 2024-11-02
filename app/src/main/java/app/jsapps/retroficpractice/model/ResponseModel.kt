package app.jsapps.retroficpractice.model

data class ResponseModel(
    val type:String,
    val popularity:Int?=null,
    val artistName:String?=null,
    val artistsName:List<Artist>? = null,
    val albumName:String? = null,
    val trackName:String? = null,
    val imageUrl:String,
    val spotifyRef:String
)
