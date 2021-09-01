package com.project.ivlab.course

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.ivlab.IvLabApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CourseViewModel : ViewModel() {

    private val _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var _properties = MutableLiveData<List<CourseProperty>>()
    val properties: LiveData<List<CourseProperty>>
        get() = _properties

    private val _navigateToSelectedCourse = MutableLiveData<CourseProperty>()
    val navigatedToSelectedProperty: LiveData<CourseProperty>
        get() = _navigateToSelectedCourse

    init {
        getCourseProperties()
    }

    private fun getCourseProperties() {
        coroutineScope.launch {
            var getCourseDeferred = IvLabApi.retroService.getCourseAll()
            try {
                val listResult = getCourseDeferred.await()
                if (listResult.isNotEmpty()) {
                    _properties.value = listResult
                }
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }
    }

    fun searchCourse(key: String) {
        coroutineScope.launch {
            val getSearchCourse = IvLabApi.retroService.searchCourse(key)
            try {
                val result = getSearchCourse.await()
                Log.e("ListSize:- ", result.size.toString())
                _properties.value = result
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun displayCourseDetails(courseProperty: CourseProperty) {
        _navigateToSelectedCourse.value = courseProperty
    }

    fun displayCourseDetailsCompelete() {
        _navigateToSelectedCourse.value = null
    }
}