package com.project.ivlab.register.spinner.kecamatan

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class KecamatanProperty(
    @Json(name = "id") val id: String,
    @Json(name = "kabupaten_id") val kabupaten_id: String,
    @Json(name = "nama") val nama: String
) : Parcelable
