package com.project.ivlab

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.project.ivlab.course.CourseProperty
import com.project.ivlab.course.enroll.EnrollResponse
import com.project.ivlab.course.materi.MateriProperty
import com.project.ivlab.home.kategori.KategoriProperty
import com.project.ivlab.login.DataUser
import com.project.ivlab.product.ProdukProperty
import com.project.ivlab.product.materiterkait.MateriTerkait
import com.project.ivlab.profil.myCourse.MyCourseProperty
import com.project.ivlab.register.RegisterResponse
import com.project.ivlab.register.spinner.kabupaten.KabupatenProperty
import com.project.ivlab.register.spinner.kecamatan.KecamatanProperty
import com.project.ivlab.register.spinner.provinsi.ProvProperty
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*


// BASE
private val BASE_URL = "https://desa-pintar.id/ivlab/public/api/"

// path Image Produk
val GET_PATH_PRODUK = "https://desa-pintar.id/ivlab/public/cover_produk/"

// path Image Course
val GET_PATH_COURSE = "https://desa-pintar.id/ivlab/public/cover_pelatihan/"

// path Icon Kategori
val GET_PATH_KATEGORY = "https://desa-pintar.id/ivlab/public/icon_kategori/"


val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()


interface IvLavService {
    // API PRODUK
    @GET("getProduk")
    fun getProductAll():
            Deferred<List<ProdukProperty>>

    @GET("getProdukById/{id}")
    fun getProdukById(
        @Path("id") id: Int
    ): Deferred<MateriTerkait>

    @GET("getProdukByIdBumdes/{id}")
    fun getMyProduk(
        @Path("id") id: Int
    ):
            Deferred<List<ProdukProperty>>
    // End API PRODUK

    // API GET DATA PROVINSI, KABUPATEN, DAN KECAMATAN
    @GET("getProvinsi")
    fun getProvinsi(): Deferred<List<ProvProperty>>

    @GET("getKabupaten/{id_provinsi}")
    fun getKabupaten(
        @Path("id_provinsi") id_provinsi: String
    ): Deferred<List<KabupatenProperty>>

    @GET("getKecamatan/{id_kabupaten}")
    fun getKecamatan(
        @Path("id_kabupaten") id_kabupaten: String
    ): Deferred<List<KecamatanProperty>>
    // END API GET DATA PROVINSI, KABUPATEN, DAN KECAMATAN


    // API GET KATEGORI PRODUK
    @GET("getKategori")
    fun getKategori(): Deferred<List<KategoriProperty>>

    // API PELATIHAN
    @GET("getPelatihan")
    fun getCourseAll():
            Deferred<List<CourseProperty>>


    @FormUrlEncoded
    @POST("searchingPelatihan")
    fun searchCourse(
        @Field("keyword") email: String,
    ): Deferred<List<CourseProperty>>

    @FormUrlEncoded
    @POST("searchingProduk")
    fun searchProduct(
        @Field("keyword") email: String,
    ): Deferred<List<ProdukProperty>>


    // aslinya ini ngambil list video aja
    // tapi namanya menyesuaikan api aja
    @GET("getDetailPelatihan/{id}")
    fun getDetailCourse(
        @Path("id") id: Int
    ):
            Deferred<MateriProperty>

    @GET("getPelatihanByEnroll/{id}")
    fun getPelatihanTerenrollByIdBUMDES(
        @Path("id") id: Int
    ):
//            Deferred<MyCourseProperty>
    Deferred<List<CourseProperty>>
    // END API PELATIHAN


    // API POST
    @FormUrlEncoded
    @POST("login")
    fun postLoginApi(
        @Field("email") email: String,
        @Field("password") password: String
    ):
            Deferred<DataUser>

    // API SIGN UP
    @FormUrlEncoded
    @POST("registrasi")
    fun postRegistrasiApi(
        @Field("nama_bumdes") nama_bumdes: String,
        @Field("desa") desa: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("provinsi") provinsi: Int,
        @Field("kabupaten") kabupaten: Int,
        @Field("kecamatan") kecamatan: Int,
    ): Deferred<RegisterResponse>

    // API ENROLL PELATIHAN
    @FormUrlEncoded
    @POST("enrollPelatihan")
    fun postEnrollCourse(
        @Field("id_bumdes") id_bumdes: Int,
        @Field("id_pelatihan") id_pelatihan: Int
    ): Deferred<EnrollResponse>

    // API MENCARI PRODUK
    @FormUrlEncoded
    @POST("searchProduk")
    fun searchProduk(
        @Field("keyword") keyword: String
    )

    // END API POST

}


object IvLabApi {
    val retroService: IvLavService by lazy {
        retrofit.create(IvLavService::class.java)
    }
}

fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val nw = connectivityManager.activeNetwork ?: return false
    val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
    return when {
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        //for other device how are able to connect with Ethernet
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
        //for check internet over Bluetooth
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
        else -> false
    }
}

