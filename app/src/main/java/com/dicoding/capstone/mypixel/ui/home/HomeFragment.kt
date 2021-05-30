package com.dicoding.capstone.mypixel.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.capstone.core.data.Resource
import com.dicoding.capstone.core.ui.PixelAdapter
import com.dicoding.capstone.mypixel.databinding.HomeFragmentBinding
import com.dicoding.capstone.mypixel.ui.detail.DetailPixelActivity
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    private var _binding: HomeFragmentBinding? = null

    private val binding get() = _binding!!

    private lateinit var pixelAdapter: PixelAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)

        _binding = HomeFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            pixelAdapter = PixelAdapter()
            pixelAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailPixelActivity::class.java)
                intent.putExtra(DetailPixelActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            homeViewModel.pixel.observe(viewLifecycleOwner, { pixel ->
                if (pixel != null) {
                    when (pixel) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            pixelAdapter.setData(pixel.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                        }
                    }
                }
            })

            with(binding.rvPixel) {
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