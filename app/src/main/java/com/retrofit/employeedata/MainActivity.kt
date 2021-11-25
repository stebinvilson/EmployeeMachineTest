package com.retrofit.employeedata

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.retrofit.employeedata.databinding.ActivityMainBinding

import com.retrofit.employeedata.model.EmployeeData
import com.retrofit.employeedata.roomdb.Employee
import com.retrofit.employeedata.roomdb.EmployeeDao
import com.retrofit.employeedata.roomdb.EmployeeDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var employeeViewmodel: EmployeeViewModel
    private lateinit var  adapter1 : EmployeeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        supportActionBar?.hide()
        val dao : EmployeeDao = EmployeeDatabase.getInstance(application).employeeDao
        val repository = EmployeeRepository(dao)
        val factory = EmployeeViewModelFactory(repository)
        employeeViewmodel = ViewModelProvider(this,factory).get(EmployeeViewModel::class.java)
        val sharedPreference =  getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)

        var  b : Boolean = sharedPreference.getBoolean("isFirstTime",false)
        if (b) {
            displaydata()
        } else {
            employeeViewmodel.getEmployee()
            var editor = sharedPreference.edit()
            editor.putBoolean("isFirstTime",true)
            editor.commit()
        }

        employeeViewmodel.empList.observe(this, Observer {
            val employeeData = it
            setdata(employeeData)

        })


        binding.employeeSearch.setOnQueryTextListener(object:
            androidx.appcompat.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter1.filter.filter(newText)
                return false
            }

        })

    }

    fun displaydata () {
        employeeViewmodel.empdatabaselist.observe(this, Observer {
            var listdata = it

            binding.emprecycler.layoutManager = LinearLayoutManager(this)
            adapter1 = EmployeeAdapter(this,listdata,{selectedemployee : Employee -> listItemClicked(selectedemployee)})
            binding.emprecycler.adapter = adapter1

        })
    }

    private fun setdata(employeeData: EmployeeData) {
        var listData : ArrayList<Employee> = ArrayList()
        employeeData.forEach {

            val employee : Employee = Employee(0,it.name,it.profile_image,it.username,it.email,it.address.street,
                it.address.suite,it.address.city,it.address.zipcode,
                it.phone, it.website,it.company?.name,it.company?.catchPhrase)
            listData.add(employee)
        }
        employeeViewmodel.insert(listData)

        binding.emprecycler.layoutManager = LinearLayoutManager(this)
        adapter1 = EmployeeAdapter(this,listData,{selectedemployee : Employee -> listItemClicked(selectedemployee)})

        binding.emprecycler.adapter = adapter1

    }

    private fun listItemClicked(selectedemployee: Employee) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("data", selectedemployee)
        startActivity(intent)

    }
}