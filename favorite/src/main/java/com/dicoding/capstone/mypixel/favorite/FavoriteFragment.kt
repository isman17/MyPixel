package com.dicoding.capstone.mypixel.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.capstone.core.ui.PixelAdapter
import com.dicoding.capstone.mypixel.favorite.databinding.FavoriteFragmentBinding
import com.dicoding.capstone.mypixel.ui.detail.DetailPixelActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    private var _binding: FavoriteFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FavoriteFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadKoinModules(favoriteModule)

        if (activity != null) {

            val pixelAdapter = PixelAdapter()
            pixelAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailPixelActivity::class.java)
                intent.putExtra(DetailPixelActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            favoriteViewModel.favoritePixel.observe(viewLifecycleOwner, { pixelData ->
                pixelAdapter.setData(pixelData)
                binding.viewEmpty.root.visibility =
                    if (pixelData.isNotEmpty()) View.GONE else View.VISIBLE
            })

            with(binding.rvFavorite) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = pixelAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}