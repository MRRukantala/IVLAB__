package com.project.ivlab.home.kategori

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.project.ivlab.databinding.CategoryItemBinding

class KategoriAdapter : ListAdapter<KategoriProperty, KategoriAdapter.ViewHolder>(DiffCallBack) {
    object DiffCallBack : DiffUtil.ItemCallback<KategoriProperty>() {
        override fun areItemsTheSame(
            oldItem: KategoriProperty,
            newItem: KategoriProperty
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: KategoriProperty,
            newItem: KategoriProperty
        ): Boolean {
            return oldItem.id == newItem.id
        }

    }

    class ViewHolder(private var binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(kategoriProperty: KategoriProperty) {
            binding.kategori = kategoriProperty
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KategoriAdapter.ViewHolder {
        return KategoriAdapter.ViewHolder(CategoryItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: KategoriAdapter.ViewHolder, position: Int) {
        val kategoriProperty = getItem(position)
        holder.itemView.setOnClickListener {
//            onClickListener.onClick(kategoriProperty)
        }
        holder.bind(kategoriProperty)
    }

    class onClickListener(val clickListener: (kategoriProperty: KategoriProperty) -> Unit) {
        fun onClick(kategoriProperty: KategoriProperty) = clickListener(kategoriProperty)
    }

}