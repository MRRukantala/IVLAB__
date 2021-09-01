package com.project.ivlab.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.ivlab.IvLabApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var _dataUser = MutableLiveData<List<Data>>()
    val dataUser: LiveData<List<Data>>
        get() = _dataUser

    private var _activeUser = MutableLiveData<Data>()
    val activeUser: LiveData<Data>
        get() = _activeUser

    fun loginApi(email: String, password: String) {
        coroutineScope.launch {
            var getCourseDeferred = IvLabApi.retroService.postLoginApi(email, password)
            try {
                val listResult = getCourseDeferred.await()
                if (listResult.message.equals("Success")) {
                    _dataUser.value = listResult.data
                    _response.value = listResult.message
                }
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
            }
        }
    }


}


