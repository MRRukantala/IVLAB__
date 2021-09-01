package com.project.ivlab.course.materi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.ivlab.IvLabApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MateriViewModel : ViewModel() {
    private val _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var _properties = MutableLiveData<List<Video>>()
    val properties: LiveData<List<Video>>
        get() = _properties

    private val _selectedMateriVideo = MutableLiveData<Video>()
    val selectedMateriVideo: LiveData<Video>
        get() = _selectedMateriVideo


    private var _id: Int = 0

    init {
        getMateriProperties()
    }

    private fun getMateriProperties() {
        coroutineScope.launch {
            var getMateriDeferred = IvLabApi.retroService.getDetailCourse(_id)
            try {
                val listResult = getMateriDeferred.await()
                if (listResult.video.size > 0) {
                    _properties.value = listResult.video
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

    fun displayVideoInView(video: Video){
        _selectedMateriVideo.value = video
    }

    fun displayVideoInVIewCompelete() {
        _selectedMateriVideo.value = null
    }


    fun valueId(id: Int) {
        _id = id
    }
}