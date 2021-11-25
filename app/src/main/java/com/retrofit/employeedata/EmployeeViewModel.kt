package com.retrofit.employeedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.retrofit.employeedata.model.EmployeeData
import com.retrofit.employeedata.roomdb.Employee
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class EmployeeViewModel(private val  repository: EmployeeRepository) : ViewModel() {

   val empdatabaselist = repository.users

    var empList = MutableLiveData<EmployeeData>()

//    fun getdatabsedata() : LiveData<EmployeeData>? {
//        empList = repository.getdata()
//        return empList
//    }

    fun getEmployee() : LiveData<EmployeeData>? {
        empList = repository.getEmployeeApiCall()
        return empList
    }


    fun insert (subscriber: List<Employee>): Job = viewModelScope.launch {
        repository.insert(subscriber)

    }


}