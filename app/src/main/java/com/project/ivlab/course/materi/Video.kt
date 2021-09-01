package com.project.ivlab.course.materi

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Video(
    val id_pelatihan: Int,
    val link_video: String,
    val judul_pelatihan: String

) : Parcelable