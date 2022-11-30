package com.example.lifehack_studio_test.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.lifehack_studio_test.databinding.ItemPlaceBinding
import com.example.lifehack_studio_test.domain.model.Place
import javax.inject.Inject

class PlacesAdapter @Inject constructor(
    private val glide: RequestManager,
) : ListAdapter<Place, PlacesAdapter.PlaceHolder>(DiffCallback()) {

    private var onItemClickListener: ((Place) -> Unit)? = null

    class PlaceHolder(val binding: ItemPlaceBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PlaceHolder(ItemPlaceBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: PlaceHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            tvPlaceNum.text = item.id.toString()
            tvPlaceInfo.text = item.name
            glide.load(item.imgUrl)
                .error(com.google.android.material.R.drawable.material_ic_clear_black_24dp)
                .into(ivPlace)
            root.setOnClickListener {
                onItemClickListener?.let { click ->
                    click(item)
                }
            }
        }
    }

    fun setItemClickListener(listener: (Place) -> Unit) {
        onItemClickListener = listener
    }

    private class DiffCallback : DiffUtil.ItemCallback<Place>() {
        override fun areItemsTheSame(oldItem: Place, newItem: Place): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Place, newItem: Place): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

}