package com.project.ivlab.profil.myCourse

import com.project.ivlab.course.CourseProperty
import com.squareup.moshi.Json

data class MyCourseProperty (
    @Json(name = "data") val listMyCourse: List<CourseProperty>
)