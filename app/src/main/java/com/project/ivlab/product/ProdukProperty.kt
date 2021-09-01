package com.project.ivlab.product

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProdukProperty(
    val id: String,
    val nama_produk: String,
    val deskripsi_produk: String,
    @Json(name = "foto_produk") val fotoProduk: String,
    val harga: String,
    val nama_bumdes: String? = null
) : Parcelable
