package com.project.ivlab.home.kategori

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class KategoriProperty(
    @Json(name = "id")val id: Int,
    @Json(name = "nama_kategori")val nama_kategori: String,
    @Json(name = "nama_file")val nama_file: String,
) : Parcelable
