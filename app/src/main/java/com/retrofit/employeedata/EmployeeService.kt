package com.retrofit.employeedata

import com.retrofit.employeedata.model.EmployeeData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface EmployeeService {

    @GET("v2/{data}")
    fun getAllMovies(@Path("data") data : String) : Call<EmployeeData>
}