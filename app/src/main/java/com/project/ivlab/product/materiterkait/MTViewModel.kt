package com.project.ivlab.product.materiterkait

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.ivlab.IvLabApi
import com.project.ivlab.course.CourseProperty
import com.project.ivlab.course.materi.Video
import kotlinx.coroutines.*

class MTViewModel: ViewModel() {

    private val _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var _properties = MutableLiveData<List<CourseProperty>>()
    val properties: LiveData<List<CourseProperty>>
        get() = _properties

    private var _id : Int = 0

    init {
        getProp()
    }

    private fun getProp() {
        coroutineScope.launch {
            var getCourseTerkait = IvLabApi.retroService.getProdukById(_id)

            try {
                val listResult = getCourseTerkait.await()
                if (listResult.pelatihan.size > 0) {
                    _properties.value = listResult.pelatihan
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

    fun valueId(id : Int) {
        _id = id
    }
}