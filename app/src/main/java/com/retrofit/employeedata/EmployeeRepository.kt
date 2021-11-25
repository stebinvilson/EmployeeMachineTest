package com.retrofit.employeedata

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.retrofit.employeedata.model.EmployeeData
import com.retrofit.employeedata.roomdb.Employee
import com.retrofit.employeedata.roomdb.EmployeeDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmployeeRepository(private val dao: EmployeeDao) {

    val EmployeeDetails = MutableLiveData<EmployeeData>()

     fun insert(subscriber: List<Employee>) {
        dao.insertEmployee(subscriber)
    }


     var users =   dao.getAll()


    fun getEmployeeApiCall(): MutableLiveData<EmployeeData> {

        val call = RetrofitClient.apiInterface.getAllMovies("5d565297300000680030a986")


        call.enqueue(object : Callback<EmployeeData> {
            override fun onFailure(call: Call<EmployeeData>, t: Throwable) {
                Log.v("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<EmployeeData>,
                response: Response<EmployeeData>
            ) {
                Log.v("DEBUG : ", response.body().toString())

                val data: EmployeeData? = response.body()

                EmployeeDetails.value = data!!
            }
        })
        return EmployeeDetails
    }
}