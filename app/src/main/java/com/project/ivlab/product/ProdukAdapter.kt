package com.project.ivlab.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.project.ivlab.course.CourseAdapter
import com.project.ivlab.databinding.ProductItemBinding

class ProdukAdapter(
    private val onClickListener: OnClickListener
) : ListAdapter<ProdukProperty, ProdukAdapter.ProdukViewHolder>(DiffCallback) {
    class ProdukViewHolder(private var binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(produkProperty: ProdukProperty) {
            binding.produk = produkProperty
            binding.executePendingBindings()
        }

    }

    object DiffCallback : DiffUtil.ItemCallback<ProdukProperty>() {
        override fun areItemsTheSame(oldItem: ProdukProperty, newItem: ProdukProperty): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ProdukProperty, newItem: ProdukProperty): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProdukAdapter.ProdukViewHolder {
        return ProdukViewHolder(ProductItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ProdukAdapter.ProdukViewHolder, position: Int) {
        val produkProperty = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(produkProperty)
        }
        holder.bind(produkProperty)
    }

    class OnClickListener(
        val clickListener: (productProperty: ProdukProperty) -> Unit
    ) {
        fun onClick(produkProperty: ProdukProperty) = clickListener(produkProperty)
    }

}