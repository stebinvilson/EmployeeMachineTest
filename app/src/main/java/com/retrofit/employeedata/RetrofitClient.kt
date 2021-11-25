package com.retrofit.employeedata


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    const val baseUrl = "http://www.mocky.io/"

    val retrofitClient: Retrofit.Builder by lazy {

//        val levelType: HttpLoggingInterceptor.Level
//        if (BuildConfig.BUILD_TYPE.contentEquals("debug"))
//            levelType = HttpLoggingInterceptor.Level.BODY else levelType = HttpLoggingInterceptor.Level.NONE
//
//        val logging = HttpLoggingInterceptor()
//        logging.setLevel(levelType)

        val okhttpClient = OkHttpClient.Builder()
      //  okhttpClient.addInterceptor(logging)

        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okhttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
    }

    val apiInterface: EmployeeService by lazy {
        retrofitClient
            .build()
            .create(EmployeeService::class.java)
    }
}