package com.dicoding.capstone.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.capstone.core.R
import com.dicoding.capstone.core.databinding.ItemListLayoutBinding
import com.dicoding.capstone.core.domain.model.Pixel
import java.util.*

class PixelAdapter : RecyclerView.Adapter<PixelAdapter.ListViewHolder>() {

    private var listData = ArrayList<Pixel>()
    var onItemClick: ((Pixel) -> Unit)? = null

    fun setData(newListData: List<Pixel>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_layout, parent, false)
        )

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListLayoutBinding.bind(itemView)

        fun bind(data: Pixel) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.largeImageURL)
                    .into(ivItemImage)
                Glide.with(itemView.context)
                    .load(data.userImageURL)
                    .into(imgItemPhoto)
                tvItemTitle.text = data.tags.replace(",","")
                tvItemSubtitle.text = data.user
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}