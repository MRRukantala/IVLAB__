package com.project.ivlab.profil.myCourse.detailmycourse

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.ivlab.course.CourseProperty
import java.lang.IllegalArgumentException

class DetailMyCourseViewModelFactory(
    private val courseProperty: CourseProperty,
    private val application: Application?
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailMyCourseViewModel::class.java)) {
            return DetailMyCourseViewModel(courseProperty, application!!) as T
        }
        throw  IllegalArgumentException("View Model dari mana nih")
    }

}
