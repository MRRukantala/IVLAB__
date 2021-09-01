package com.project.ivlab.course.materi

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.project.ivlab.course.CourseProperty
import com.project.ivlab.databinding.MateriCourseItemBinding
import com.project.ivlab.home.kategori.KategoriAdapter

class MateriAdapter(private val onClickListener: OnClickListener) :
    androidx.recyclerview.widget.ListAdapter<Video, MateriAdapter.MateriViewHolder>(
        DiffCallback
    ) {
    object DiffCallback : DiffUtil.ItemCallback<Video>() {
        override fun areItemsTheSame(oldItem: Video, newItem: Video): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Video, newItem: Video): Boolean {
            return oldItem.id_pelatihan == newItem.id_pelatihan
        }

    }

    class MateriViewHolder(private var binding: MateriCourseItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(video: Video) {
            binding.video = video
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MateriViewHolder {
        return MateriViewHolder(
            MateriCourseItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MateriViewHolder, position: Int) {

        val videoPropery = getItem(position)

        holder.itemView.setOnClickListener {
            onClickListener.onClick(videoPropery)
        }
        holder.bind(videoPropery)
    }

    class OnClickListener(val clickListener: (video: Video) -> Unit){
        fun onClick(video: Video) = clickListener(video)
    }

}