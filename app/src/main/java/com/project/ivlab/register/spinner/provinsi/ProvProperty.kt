package com.project.ivlab.register.spinner.provinsi

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProvProperty(
    @Json(name = "id") val id: String,
    @Json(name = "nama") val nama: String
) : Parcelable
