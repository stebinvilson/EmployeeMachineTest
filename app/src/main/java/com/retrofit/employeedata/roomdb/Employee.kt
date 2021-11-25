package com.retrofit.employeedata.roomdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "EmployeeTable",)
data class Employee (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "employeeId")
    var id: Int,

    @ColumnInfo(name = "employeeName")
    var name: String?,

    @ColumnInfo(name = "employeeImage")
    var image: String?,

    @ColumnInfo(name = "username")
    var username: String?,

    @ColumnInfo(name = "employeeEmail")
    var email: String?,

    @ColumnInfo(name = "street")
    var street: String?,

    @ColumnInfo(name = "suite")
    var suite: String?,

    @ColumnInfo(name = "city")
    var city: String?,

    @ColumnInfo(name = "zipcode")
    var zipcode: String?,

    @ColumnInfo(name = "phone")
    var phone: String?,

    @ColumnInfo(name = "website")
    var website: String?,

    @ColumnInfo(name = "companyname")
    var companyname: String?,

    @ColumnInfo(name = "catchPhrase")
    var catchPhrase: String?
) : Serializable


