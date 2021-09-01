package com.project.ivlab.product

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.project.ivlab.GET_PATH_COURSE
import com.project.ivlab.GET_PATH_KATEGORY
import com.project.ivlab.GET_PATH_PRODUK
import com.project.ivlab.R
import com.project.ivlab.course.CourseAdapter
import com.project.ivlab.course.CourseProperty
import com.project.ivlab.course.materi.MateriAdapter
import com.project.ivlab.course.materi.MateriProperty
import com.project.ivlab.course.materi.Video
import com.project.ivlab.home.kategori.KategoriAdapter
import com.project.ivlab.home.kategori.KategoriProperty

@BindingAdapter("listDataProduk")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<ProdukProperty>?) {
    val adapter = recyclerView.adapter as ProdukAdapter
    adapter.submitList(data)
}

@BindingAdapter("listDataCourse")
fun bindRecylerViewCourse(recyclerView: RecyclerView, data: List<CourseProperty>?) {
    val adapter = recyclerView.adapter as CourseAdapter
    adapter.submitList(data)
}

@BindingAdapter("listDataKategori")
fun bindRecyclerViewKategori(recyclerView: RecyclerView, data: List<KategoriProperty>?){
    val adapter = recyclerView.adapter as KategoriAdapter
    adapter.submitList(data)
}

@BindingAdapter("listMateriCourse")
fun bindRecylerViewMateriCourse(recyclerView: RecyclerView, data: List<Video>?) {
    val adapter = recyclerView.adapter as MateriAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        Glide.with(imgView.context)
            .load(GET_PATH_PRODUK + imgUrl)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imgView)
    }
}

@BindingAdapter("imageUrlCourse")
fun bindImageCourse(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        Glide.with(imgView.context)
            .load(GET_PATH_COURSE + imgUrl)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imgView)
    }
}

@BindingAdapter("nama_file")
fun bindImageKategori(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        Glide.with(imgView.context)
            .load(GET_PATH_KATEGORY + imgUrl)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imgView)
    }
}
