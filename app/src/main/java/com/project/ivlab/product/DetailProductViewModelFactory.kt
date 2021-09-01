package com.project.ivlab.product

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class DetailProductViewModelFactory(
    private val productPropery: ProdukProperty,
    private val application: Application?
) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DetailProductViewModel::class.java)){
            return DetailProductViewModel(productPropery, application!!) as T
        }
        throw IllegalArgumentException("View Model dari mana nih")
    }

}
