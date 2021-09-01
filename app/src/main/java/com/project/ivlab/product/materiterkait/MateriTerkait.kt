package com.project.ivlab.product.materiterkait

import com.project.ivlab.course.CourseProperty
import com.project.ivlab.product.ProdukProperty
import com.squareup.moshi.Json

data class MateriTerkait(
    @Json(name ="data") val data: List<ProdukPropertyTerkait>,
    @Json(name ="pelatihan") val pelatihan: List<CourseProperty>
)
