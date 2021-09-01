package com.project.ivlab.profil.myCourse

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.ivlab.IvLabApi
import com.project.ivlab.course.CourseProperty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MyCourseViewModel : ViewModel() {

    private val _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _property = MutableLiveData<List<CourseProperty>>()
    val property: LiveData<List<CourseProperty>>
        get() = _property

    private val _navigateToSelectedCourse = MutableLiveData<CourseProperty>()
    val navigatedToSelectedProperty: LiveData<CourseProperty>
        get() = _navigateToSelectedCourse

    private var _id: Int = 0

    init {
        getProperties()
    }

    private fun getProperties() {
        coroutineScope.launch {
            var getMyCourse = IvLabApi.retroService.getPelatihanTerenrollByIdBUMDES(_id)

            try {
                val listMyResult = getMyCourse.await()
                if (listMyResult.size > 0) {
                    _property.value = listMyResult
                }

            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun valueId(id: Int) {
        _id = id
    }

    fun displayMyCourseDetails(courseProperty: CourseProperty) {
        _navigateToSelectedCourse.value = courseProperty
    }

    fun displayMyCourseDetailsCompelete() {
        _navigateToSelectedCourse.value = null
    }
}
