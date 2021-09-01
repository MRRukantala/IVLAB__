package com.project.ivlab.course.enroll

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.ivlab.IvLabApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class EnrollViewModel : ViewModel() {

    private val _unroll = MutableLiveData<EnrollResponse>()
    val unroll: LiveData<EnrollResponse>
        get() = _unroll

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    fun enrollCourse(id_bumdes: Int, id_pelatihan: Int) {
        coroutineScope.launch {
            var postEnrollCourse = IvLabApi.retroService.postEnrollCourse(id_bumdes, id_pelatihan)
            try {
                val result = postEnrollCourse.await()
                _unroll.value = result
            } catch (e: Exception) {
                _unroll.value!!.status = "Failure: ${e.message}"
            }

        }
    }

}