package com.project.ivlab.profil

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.ivlab.IvLabApi
import com.project.ivlab.product.ProdukProperty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MyProductViewModel : ViewModel() {

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    private var _id : Int = 0

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var _properties = MutableLiveData<List<ProdukProperty>>()
    val properties: LiveData<List<ProdukProperty>>
        get() = _properties

    private var _navigateToSelectedMyProduct = MutableLiveData<ProdukProperty>()
    val navigatedToSelectedProperty: LiveData<ProdukProperty>
        get() = _navigateToSelectedMyProduct

    init {
        getMyProductProperties()
    }

    private fun getMyProductProperties() {
        coroutineScope.launch {
            Log.v("ID", _id.toString())
            var getPropertiesDefered = IvLabApi.retroService.getMyProduk(_id)
            try {

                val listResult = getPropertiesDefered.await()
                if (listResult.size > 0) {
                    Log.v("value", listResult.size.toString())
                    _properties.value = listResult
                }
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
            }
        }
    }

    fun displayProductDetails(produkProperty: ProdukProperty) {
        _navigateToSelectedMyProduct.value = produkProperty
    }

    fun displayProdcutDetailsCompelete() {
        _navigateToSelectedMyProduct.value = null
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun valueId(id : Int) {
        _id = id
    }

}