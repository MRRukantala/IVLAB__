package com.project.ivlab.course

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class DetailCourseViewModel(
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