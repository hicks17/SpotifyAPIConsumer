package app.jsapps.retroficpractice.view.adapter

import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.jsapps.retroficpractice.R
import app.jsapps.retroficpractice.databinding.TrackLayoutBinding
import app.jsapps.retroficpractice.model.ResponseModel
import app.jsapps.retroficpractice.model.TrackModel
import com.bumptech.glide.Glide

class TrackRVAdapter : RecyclerView.Adapter<TrackRVAdapter.TrackVH>() {

    var data = mutableListOf<ResponseModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackVH {
        val view = LayoutInflater.from(parent.context)

            .inflate(R.layout.track_layout, parent, false)

        return TrackVH(view)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: TrackVH, position: Int) {
        holder.putData(data[position])
    }

    inner class TrackVH(private val view: View) : RecyclerView.ViewHolder(view) {

        private val bind = TrackLayoutBinding.bind(view)
        fun putData(trackModel: ResponseModel){


            bind.container.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(trackModel.spotifyRef)

                    view.context.startActivity(intent)

            }
            Log.w(TAG, "model: $trackModel" )
            when(trackModel.type) {

                "track" -> {
                    with(bind) {
                        tvTrack.visibility = View.VISIBLE
                        tvArtist.visibility = View.VISIBLE
                        tvTrack.text = trackModel.trackName
                        tvArtist.text = "Canción - ${trackModel.artistsName!![0].name}"
                        ivArtist.visibility = View.INVISIBLE
                        artsistName.visibility = View.INVISIBLE
                        ivAlbum.visibility = View.VISIBLE
                        Glide.with(view)
                            .load(trackModel.imageUrl)
                            .into(ivAlbum)
                    }
                }
                "album"-> {
                    with(bind) {
                        artsistName.visibility = View.INVISIBLE
                        ivArtist.visibility = View.INVISIBLE
                        ivAlbum.visibility = View.VISIBLE
                        tvTrack.visibility = View.VISIBLE
                        tvArtist.visibility = View.VISIBLE
                        tvTrack.text = trackModel.albumName
                        tvArtist.text = "Album - ${trackModel.artistsName?.get(0)?.name}"

                        Glide.with(view)
                            .load(trackModel.imageUrl)
                            .into(ivAlbum)
                    }
                }
                "artist"-> {
                    with(bind) {
                        artsistName.text = trackModel.artistName
                        artsistName.visibility = View.VISIBLE
                        tvArtist.visibility = View.INVISIBLE
                        tvTrack.visibility = View.INVISIBLE
                        ivArtist.visibility = View.VISIBLE
                        ivAlbum.visibility = View.INVISIBLE

                        Glide.with(view)
                            .load(trackModel.imageUrl)
                            .into(ivArtist)
                    }
                }
            }
        }
    }
}


