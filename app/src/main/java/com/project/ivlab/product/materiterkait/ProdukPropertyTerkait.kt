package com.project.ivlab.product.materiterkait

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProdukPropertyTerkait(
    val id: String,
    val nama_produk: String,
    val deskripsi_produk: String,
    @Json(name = "foto_produk") val fotoProduk: String,
    val harga: String
) : Parcelable