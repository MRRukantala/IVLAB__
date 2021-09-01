package com.project.ivlab.course

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.project.ivlab.databinding.CourseItemBinding
import com.project.ivlab.product.ProdukAdapter

class CourseAdapter(private val onClickListener: OnClickListener) : ListAdapter<CourseProperty, CourseAdapter.CourseViewHolder>(DiffCallback) {
    class CourseViewHolder(private var binding: CourseItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(courseProperty: CourseProperty) {
            binding.course = courseProperty
            binding.executePendingBindings()
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<CourseProperty>() {
        override fun areItemsTheSame(oldItem: CourseProperty, newItem: CourseProperty): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: CourseProperty, newItem: CourseProperty): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CourseViewHolder {
        return CourseViewHolder(CourseItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val courseProperty = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(courseProperty)
        }
        holder.bind(courseProperty)
    }

    class OnClickListener(val clickListener: (courseProperty : CourseProperty) -> Unit){
        fun onClick(courseProperty: CourseProperty) = clickListener(courseProperty)
    }

}
