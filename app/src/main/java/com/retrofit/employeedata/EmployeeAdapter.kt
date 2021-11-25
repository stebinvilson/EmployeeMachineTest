package com.retrofit.employeedata

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.retrofit.employeedata.databinding.EmployeeitemBinding
import com.retrofit.employeedata.roomdb.Employee
import android.R
import android.graphics.drawable.Drawable
import android.widget.Filter
import android.widget.Filterable

import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target


class EmployeeAdapter(var context: Context,var employeedata : List<Employee>,private val clickListener: (Employee) -> Unit) : RecyclerView.Adapter<MovieViewHolder>(),
    Filterable {
    var employees :List<Employee> = ArrayList()

    init {
        employees = employeedata.toMutableList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = EmployeeitemBinding.inflate(inflater, parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        val employeedetails = employees[position]
        holder.binding.txtName.text = employeedetails.name
        holder.binding.companyname.text  = employeedetails.companyname
        Glide.with(holder.itemView.context)
            .load(employeedetails.image)
            .into(holder.binding.profileImage)
//        Glide.with(holder.itemView.context)    load().into(holder.binding.profileImage)

        holder.binding.layoutitem.setOnClickListener(View.OnClickListener {
            clickListener(employeedetails)
        })

    }

    override fun getItemCount(): Int {
        return employees.size
    }



    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    employees = employeedata
                } else {
                    val resultList = ArrayList<Employee>()
                    for (row in employeedata) {
                        if (row.name?.lowercase()?.contains(charSearch) == true || row.email?.lowercase()?.contains(charSearch) == true) {
                            resultList.add(row)
                        }
                    }
                    employees = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = employees
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                employees = results?.values as ArrayList<Employee>
                notifyDataSetChanged()
            }

        }
    }
}

class MovieViewHolder(val binding: EmployeeitemBinding) : RecyclerView.ViewHolder(binding.root) {

}
