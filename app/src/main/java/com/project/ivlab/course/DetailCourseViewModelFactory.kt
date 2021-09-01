package com.project.ivlab.course

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class DetailCourseViewModelFactory(
    private val courseProperty: CourseProperty,
    private val application: Application?
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailCourseViewModel::class.java)) {
            return DetailCourseViewModel(courseProperty, application!!) as T
        }
        throw  IllegalArgumentException("View Model dari mana nih")
    }

}
