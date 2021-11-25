package com.retrofit.employeedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.retrofit.employeedata.databinding.ActivityDetailsBinding


import com.retrofit.employeedata.roomdb.Employee

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = DataBindingUtil.setContentView(this,R.layout.activity_details)
        receivedata()
    }

    private fun receivedata() {
        val employee = intent.getSerializableExtra("data") as? Employee
        Glide.with(applicationContext)
            .load(employee?.image)
            .into(binding.empimage)
        binding.employee = employee

    }
}