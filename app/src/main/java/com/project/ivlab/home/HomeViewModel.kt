package com.project.ivlab.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.ivlab.IvLabApi
import com.project.ivlab.course.CourseProperty
import com.project.ivlab.home.kategori.KategoriProperty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status


    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var _courseProperties = MutableLiveData<List<CourseProperty>>()
    val courseProperty: LiveData<List<CourseProperty>>
        get() = _courseProperties

    private var _kategoriProperties = MutableLiveData<List<KategoriProperty>>()
    val kategoriProperty: LiveData<List<KategoriProperty>>
        get() = _kategoriProperties

    private val _navigateToSelectedCourse = MutableLiveData<CourseProperty>()
    val navigatedToSelectedProperty: LiveData<CourseProperty>
        get() = _navigateToSelectedCourse

//    private var _categoryProperties = MutableLiveData<List<CategoryProperty>>()
//    val categoryProperty: LiveData<List<CategoryProperty>>
//        get() = _categoryProperties

    init {
        getCourseProperties()
        getKategoriProperties()
    }

    private fun getCourseProperties() {
        coroutineScope.launch {
            var getCourseDeferred = IvLabApi.retroService.getCourseAll()
            try {
                val listResult = getCourseDeferred.await()
                if (listResult.isNotEmpty()) {

                    _courseProperties.value = listResult
                }
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }
    }

    private fun getKategoriProperties(){
        coroutineScope.launch {
            var getKategoriDeferred = IvLabApi.retroService.getKategori()
            try {
                val listResult = getKategoriDeferred.await()
                if (listResult.size > 0) {
                    Log.v("Kategori", listResult.size.toString())
                    _kategoriProperties.value = listResult
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

    fun displayCourseDetails(courseProperty: CourseProperty) {
        _navigateToSelectedCourse.value = courseProperty
    }

    fun displayCourseDetailsCompelete() {
        _navigateToSelectedCourse.value = null
    }
}