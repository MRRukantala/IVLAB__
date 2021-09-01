package com.project.ivlab.register

import android.R
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.ivlab.IvLabApi
import com.project.ivlab.register.spinner.kabupaten.KabupatenProperty
import com.project.ivlab.register.spinner.kecamatan.KecamatanProperty
import com.project.ivlab.register.spinner.provinsi.ProvProperty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    private val _response = MutableLiveData<RegisterResponse>()
    val response: LiveData<RegisterResponse>
        get() = _response


    private val provinceList: ArrayList<ProvProperty> = ArrayList()
    private var provinceId = ""
    private val KabupatenPropertyList: ArrayList<KabupatenProperty> = ArrayList()
    private var KabupatenPropertyId = ""
    private val KecamatanPropertyList: ArrayList<KecamatanProperty> = ArrayList()
    private var KecamatanPropertyId = ""

    private val _provinsiId = MutableLiveData<Int>()
    val provinsiId: LiveData<Int>
        get() = _provinsiId

    private val _kabupatenId = MutableLiveData<Int>()
    val kabupatenId: LiveData<Int>
        get() = _kabupatenId

    private val _kecamatanId = MutableLiveData<Int>()
    val kecamatanId: LiveData<Int>
        get() = _kecamatanId


/*    private val _provincies = MutableLiveData<ArrayList<ProvProperty>>()
    val provincies: LiveData<ArrayList<ProvProperty>>
        get() = _provincies*/


/*
    private val _idProvinsiSelected = MutableLiveData<String>()
    val idProvinsiSelected : LiveData<String>
        get() = _idProvinsiSelected

    private val _kabupaten = MutableLiveData<List<KabupatenProperty>>()
    val kabupaten: LiveData<List<KabupatenProperty>>
        get() = _kabupaten

    private val _idKabupatenSelected = MutableLiveData<String>()
    val idKabupatenSelected : LiveData<String>
        get() = _idKabupatenSelected

    private val _kecamatan = MutableLiveData<List<KecamatanProperty>>()
    val kecamatan: LiveData<List<KecamatanProperty>>
        get() = _kecamatan

    private val _idKecamatanSelected = MutableLiveData<String>()
    val idKecamatanSelected : LiveData<String>
        get() = _idKecamatanSelected
*/

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getProvinsi()
    }


    private fun getProvinsi() {
        coroutineScope.launch {
            val getProvinsi = IvLabApi.retroService.getProvinsi()

            try {
                val result = getProvinsi.await()
                Log.e("Provinsi", result.toString())
                if (result.isNotEmpty()) {
                    provinceList.clear()
                    provinceList.addAll(result as ArrayList)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun onClickProvince(view: View) {
        val adapter = ArrayAdapter<String>(
            view.context,
            R.layout.simple_list_item_1, provinceList.joinToString { it.nama }.split(",")
        )
        (view as AutoCompleteTextView).setAdapter(adapter)
        (view).showDropDown()
        (view).onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                Log.e("uguguyfgugu", provinceList[position].id)
                provinceId = provinceList[position].id
                _provinsiId.value = provinceId.toInt()
                Regis2Fragment.binding.kabupaten.setText("")
                Regis2Fragment.binding.kecamatan.setText("")
                getKabupaten()
            }
    }

    private fun getKabupaten() {
        coroutineScope.launch {
            var getKabupaten = IvLabApi.retroService.getKabupaten(provinceId)

            try {
                val result = getKabupaten.await()
                Log.v("Kabupaten", result.toString())
                if (result.isNotEmpty()) {
                    KabupatenPropertyList.clear()
                    KabupatenPropertyList.addAll(result as ArrayList)
                }
            } catch (e: Exception) {

            }
        }
    }

    fun onClickKabupaten(view: View) {
        val adapter = ArrayAdapter<String>(
            view.context,
            R.layout.simple_list_item_1, KabupatenPropertyList.joinToString { it.nama }.split(",")
        )
        (view as AutoCompleteTextView).setAdapter(adapter)
        (view).showDropDown()
        (view).onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                Log.e("uguguyfgugu", KabupatenPropertyList[position].id)
                KabupatenPropertyId = KabupatenPropertyList[position].id
                _kabupatenId.value = KabupatenPropertyId.toInt()
                Regis2Fragment.binding.kecamatan.setText("")
                getKecamatan()
            }
    }

    fun getKecamatan() {
        coroutineScope.launch {
            var getKecamatan = IvLabApi.retroService.getKecamatan(KabupatenPropertyId)

            try {
                val result = getKecamatan.await()
                Log.v("Kecamatan", result.toString())
                if (result.size > 0) {
                    KecamatanPropertyList.clear()
                    KecamatanPropertyList.addAll(result as ArrayList)
                }
            } catch (e: Exception) {

            }
        }
    }

    fun onClickKecamatan(view: View) {
        val adapter = ArrayAdapter<String>(
            view.context,
            R.layout.simple_list_item_1, KecamatanPropertyList.joinToString { it.nama }.split(",")
        )
        (view as AutoCompleteTextView).setAdapter(adapter)
        (view).showDropDown()
        (view).onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                Log.e("uguguyfgugu", KecamatanPropertyList[position].id)
                KecamatanPropertyId = KecamatanPropertyList[position].id
                _kecamatanId.value = KecamatanPropertyId.toInt()
            }
    }

    fun registrasi(
        nama_bumdes: String,
        desa: String,
        email: String,
        password: String,
        provinsi: Int,
        kabupaten: Int,
        kecamatan: Int
    ) {
        coroutineScope.launch {
            val postRegistrasi = IvLabApi.retroService.postRegistrasiApi(
                nama_bumdes, desa, email, password, provinsi, kabupaten, kecamatan
            ).await()

            try {
                val result = postRegistrasi
                _response.value = result
            } catch (e: Exception) {
//                _response.value!!.message = postRegistrasi.message
            }

        }
    }

    override fun onCleared() {
        super.onCleared()
    }

    fun successApi() {
        _response.value = null
    }

}