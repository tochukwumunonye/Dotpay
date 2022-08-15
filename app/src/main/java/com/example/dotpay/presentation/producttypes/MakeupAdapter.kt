package com.example.dotpay.presentation.producttypes

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.dotpay.R
import com.example.dotpay.databinding.MakeupItemBinding
import com.example.dotpay.domain.model.Makeup
import com.squareup.picasso.Picasso
import okhttp3.internal.assertThreadDoesntHoldLock

class MakeupAdapter(): ListAdapter<Makeup, MakeupAdapter.MakeupAdapterViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MakeupAdapterViewHolder {
        val binding =
            MakeupItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MakeupAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MakeupAdapterViewHolder, position: Int) {
        val currentAlbum = getItem(position)

        if(currentAlbum != null) {
            holder.bind(currentAlbum)
        }
    }

    inner class MakeupAdapterViewHolder(private val binding: MakeupItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Makeup) {

            Glide.with(binding.root.context)
                .asBitmap()
                .error(R.drawable.downloadw)
                .placeholder(R.drawable.downloadw)
                .load(item.image_link)
               .centerCrop()
                .into(binding.makeupImage)

            binding.makeupDescription.text = item.description
            binding.makeupName.text = item.name
            binding.makeupPrice.text = item.price

        }

    }
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Makeup>() {
            override fun areItemsTheSame(oldItem: Makeup, newItem: Makeup): Boolean {
                return oldItem.name == newItem.name
            }
            override fun areContentsTheSame(oldItem: Makeup, newItem: Makeup): Boolean {
                return oldItem.brand == newItem.brand
            }
        }
    }
}