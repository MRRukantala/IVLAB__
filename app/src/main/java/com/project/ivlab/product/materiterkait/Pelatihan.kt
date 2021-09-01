package com.project.ivlab.product.materiterkait

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pelatihan(
    val id: String,
    val nama_pelatihan: String,
    val deskripsi: String,
    @Json(name = "foto_cover") val fotoCover: String,
    val id_bumdes: String,
    val mentor: String
) : Parcelable
