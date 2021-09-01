package com.project.ivlab.login

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


//@Parcelize
data class DataUser(
    val message: String,
    val data: List<Data>
)
