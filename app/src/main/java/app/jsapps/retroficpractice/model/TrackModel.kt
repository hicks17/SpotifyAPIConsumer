package app.jsapps.retroficpractice.model

data class TrackModel(
    val name:String,
    val artist:List<ArtistModel>,
    val albumUrl:String,
    val duration:Int
)