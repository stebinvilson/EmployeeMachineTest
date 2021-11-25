package com.retrofit.employeedata.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Employee ::class],version = 1)
abstract class EmployeeDatabase : RoomDatabase() {

    abstract val employeeDao : EmployeeDao

    companion object {
        @Volatile
        private var INSTANCE : EmployeeDatabase? = null
        fun getInstance(context: Context) : EmployeeDatabase {
            synchronized(this) {
                var instance :EmployeeDatabase? = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext,EmployeeDatabase ::class.java,"employee_database").allowMainThreadQueries().build()
                }
                return instance
            }
        }
    }
}