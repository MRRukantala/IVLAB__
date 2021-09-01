package com.project.ivlab.product

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.ivlab.IvLabApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ProdukViewModel : ViewModel() {

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var _properties = MutableLiveData<List<ProdukProperty>>()
    val properties: LiveData<List<ProdukProperty>>
        get() = _properties

    private var _navigateToSelectedProduct = MutableLiveData<ProdukProperty>()
    val navigatedToSelectedProperty: LiveData<ProdukProperty>
        get() = _navigateToSelectedProduct

    init {
        getMarsRealEstateProperties()
    }

    private fun getMarsRealEstateProperties() {
        coroutineScope.launch {
            var getPropertiesDefered = IvLabApi.retroService.getProductAll()
            try {
                val listResult = getPropertiesDefered.await()
                if (listResult.isNotEmpty()) {
                    _properties.value = listResult
                }

            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
            }
        }
    }

    fun searchProduct(key: String) {
        coroutineScope.launch {
            val getSearchProduct = IvLabApi.retroService.searchProduct(key)
            try {
                val result = getSearchProduct.await()
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

    fun displayProductDetails(produkProperty: ProdukProperty) {
        _navigateToSelectedProduct.value = produkProperty
    }

    fun displayProdcutDetailsCompelete() {
        _navigateToSelectedProduct.value = null
    }
}