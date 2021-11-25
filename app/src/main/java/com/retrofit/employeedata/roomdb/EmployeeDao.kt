package com.retrofit.employeedata.roomdb

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface EmployeeDao {

    @Insert
    fun insertEmployee(employee: List<Employee> )

    @Update
    fun updateEmployee(employee: Employee)

    @Delete
     fun deleteEmployee(employee: Employee)


    @Query("SELECT * FROM EmployeeTable")
    fun getAll() : LiveData<List<Employee>>
}