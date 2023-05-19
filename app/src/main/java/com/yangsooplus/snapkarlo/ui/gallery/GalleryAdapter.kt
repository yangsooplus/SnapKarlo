package com.yangsooplus.snapkarlo.ui.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yangsooplus.snapkarlo.databinding.ItemGalleryBinding
import com.yangsooplus.snapkarlo.ui.model.ImageUi

class GalleryAdapter(
    private val onItemClicked: (ImageUi) -> Unit
) : ListAdapter<ImageUi, GalleryViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        return GalleryViewHolder(
            ItemGalleryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.bind(currentList[position], onItemClicked)
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<ImageUi>() {
            override fun areItemsTheSame(oldItem: ImageUi, newItem: ImageUi): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ImageUi, newItem: ImageUi): Boolean {
                return oldItem == newItem
            }
        }
    }
}

class GalleryViewHolder(
    private val binding: ItemGalleryBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(imageUi: ImageUi, onItemClicked: (ImageUi) -> Unit) {
        binding.ivItemGallery.setImageBitmap(imageUi.bitmap)
        binding.ivItemGallery.setOnClickListener { onItemClicked(imageUi) }
    }
}
