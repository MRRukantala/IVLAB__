package com.project.ivlab.product

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class DetailProductViewModel(
    productPropery: ProdukProperty,
    app: Application
) : AndroidViewModel(app) {
    private val _selectedProperty = MutableLiveData<ProdukProperty>()
    val selectedProperty: LiveData<ProdukProperty>
        get() = _selectedProperty

    init {
        _selectedProperty.value = productPropery
    }

}
