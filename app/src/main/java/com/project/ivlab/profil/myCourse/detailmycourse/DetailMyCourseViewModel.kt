package com.project.ivlab.profil.myCourse.detailmycourse

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.project.ivlab.course.CourseProperty
import com.project.ivlab.course.materi.Video

class DetailMyCourseViewModel(
    courseProperty: CourseProperty,
    app: Application
) : AndroidViewModel(app){

    private val _selectedProperty = MutableLiveData<CourseProperty>()
    val selectedProperty: LiveData<CourseProperty>
        get() = _selectedProperty




    init {
        _selectedProperty.value = courseProperty
    }

}