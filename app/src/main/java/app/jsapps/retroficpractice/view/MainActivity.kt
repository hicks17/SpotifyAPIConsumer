package app.jsapps.retroficpractice.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.Visibility
import app.jsapps.retroficpractice.R
import app.jsapps.retroficpractice.databinding.ActivityMainBinding
import app.jsapps.retroficpractice.view.adapter.TrackRVAdapter
import app.jsapps.retroficpractice.viewmodel.TrackViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter:TrackRVAdapter
    private val trackVM: TrackViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = TrackRVAdapter()

        initListeners()
        initFlows()

    }

    private fun initFlows() {
        lifecycleScope.launch {
            trackVM.responseResult.collect {
                if (it.isEmpty()){
                    binding.chipGroup.visibility = View.GONE
                }else{
                    binding.chipGroup.visibility = View.VISIBLE
                }
                adapter.data = it.toMutableList()
                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun initListeners() {
        binding.apply {
            chipAlbums.setOnClickListener {
                chipAlbums.isChecked = true
                trackVM.searchAlbumsByName(binding.searchView.text.toString())
                initFlows()
            }
            chipArtist.setOnClickListener {
                chipArtist.isChecked = true
                trackVM.searchArtistsByName(binding.searchView.text.toString())
                initFlows()
            }
            chipCanciones.setOnClickListener {
                chipCanciones.isChecked = true
                trackVM.searchSongsByName(binding.searchView.text.toString())
                initFlows()
            }
            chipTop.setOnClickListener {
                chipTop.isChecked = true
                trackVM.getItemsByQuery(binding.searchView.text.toString())
            }
            rvResult.adapter = adapter
            rvResult.layoutManager = LinearLayoutManager(this@MainActivity)
            binding.search.setOnClickListener {
                chipTop.isChecked = true
                trackVM.getItemsByQuery(binding.searchView.text.toString())
            }
        }
    }
}