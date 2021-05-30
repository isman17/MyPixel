package com.dicoding.capstone.mypixel.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.dicoding.capstone.core.domain.model.Pixel
import com.dicoding.capstone.mypixel.R
import com.dicoding.capstone.mypixel.databinding.ActivityDetailPixelBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailPixelActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private lateinit var binding: ActivityDetailPixelBinding

    private val detailPixelViewModel: DetailPixelViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPixelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        val detailPixel = intent.getParcelableExtra<Pixel>(EXTRA_DATA)
        showDetailPixel(detailPixel)
    }

    @SuppressLint("SetTextI18n")
    private fun showDetailPixel(detailPixel: Pixel?) {
        detailPixel?.let {
            supportActionBar?.title = detailPixel.tags.replace(",", "")

            Glide.with(this)
                .load(detailPixel.userImageURL)
                .into(binding.content.imgItemUser)

            binding.content

            binding.content.tvDetailUser.text = detailPixel.user
            binding.content.tvDetailResolution.text =
                "${detailPixel.imageWidth}x${detailPixel.imageWidth}"
            binding.content.tvDetailViews.text = detailPixel.views.toString()
            binding.content.tvDetailDownloads.text = detailPixel.downloads.toString()
            binding.content.tvDetailLikes.text = detailPixel.likes.toString()
            binding.content.tvDetailComments.text = detailPixel.comments.toString()
            Glide.with(this)
                .load(detailPixel.largeImageURL)
                .into(binding.ivDetailImage)

            var statusFavorite = detailPixel.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailPixelViewModel.setFavoriteTourism(detailPixel, statusFavorite)
                setStatusFavorite(statusFavorite)
                if (statusFavorite) message(getString(R.string.added_favorite)) else message(
                    getString(R.string.removed_favorite)
                )
            }
        }
    }

    private fun message(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_baseline_favorite_24
                )
            )
        } else {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_baseline_favorite_border_24
                )
            )
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}