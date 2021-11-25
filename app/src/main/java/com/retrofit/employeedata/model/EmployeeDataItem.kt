package com.retrofit.employeedata.model

import com.retrofit.employeedata.model.Address
import com.retrofit.employeedata.model.Company

data class EmployeeDataItem(
    val address: Address,
    val company: Company?,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String?,
    val profile_image: String,
    val username: String?,
    val website: String?
)