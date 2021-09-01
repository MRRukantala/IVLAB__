package com.project.ivlab.login

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data(
    val id: Int,
    val nama_bumdes: String,
    val desa: String,
    val email: String,
    val password: String
) : Parcelable
