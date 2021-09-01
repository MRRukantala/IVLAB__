package com.project.ivlab.register.spinner.kabupaten


import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class KabupatenProperty(
    @Json(name = "id") val id: String,
    @Json(name = "provinsi_id") val provinsi_id: String,
    @Json(name = "nama") val nama: String
) : Parcelable