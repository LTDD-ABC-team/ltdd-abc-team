package com.example.apptimviec

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView

class JobAdapter (val context: Context, val JobSource:ArrayList<Job>)
    : RecyclerView.Adapter<JobAdapter.ViewHolder>(){

    var jobFilterList = ArrayList<Job>()
    var onItemClick: ((Job) -> Unit)? = null

    init {
        jobFilterList = JobSource
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_job,
                parent,false))
    }

    override fun getItemCount(): Int {
        return jobFilterList.count()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return jobFilterList[position].id.toLong()
    }
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.job.text = jobFilterList[position].job.toString()
        holder.company.text = jobFilterList[position].company.toString()
        holder.address.text = jobFilterList[position].address.toString()
        holder.salary.text = jobFilterList[position].salary.toString()

        holder.date.text = jobFilterList[position].date.toString()

        val db = DBSqllite(context)
        val pre = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE)

        if(db.checkHistory(jobFilterList[position].id,pre.getInt("user_id",1))){
            holder.btn.setChecked(true)
            //  Toast.makeText(context, "Đã lưu!", Toast.LENGTH_LONG).show();
        }

        holder.btn.setOnClickListener{

            if(holder.btn.isChecked){
                if(pre != null)
                {
                    db.insertDataHistory(jobFilterList[position].id,pre.getInt("user_id",1))
                    Toast.makeText(context, "Đã lưu!", Toast.LENGTH_LONG).show();
                }
            }
            else{
                if(pre != null)
                {
                    db.deleteDataHistory(jobFilterList[position].id,pre.getInt("user_id",1))
                    Toast.makeText(context, jobFilterList[position].id.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }
    }
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        init {
            view.setOnClickListener {
                onItemClick?.invoke(jobFilterList[adapterPosition])
            }
        }
        val job = view.findViewById<TextView>(R.id.textJob)
        val company = view.findViewById<TextView>(R.id.textCompany)
        val address = view.findViewById<TextView>(R.id.textAddress)
        val salary = view.findViewById<TextView>(R.id.textSalary)
        val date = view.findViewById<TextView>(R.id.textDate)
        val btn = view.findViewById<ToggleButton>(R.id.toggleButtonStar)
    }

    fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()

                if (charSearch.isEmpty()) {
                    jobFilterList = JobSource
                } else {
                    val resultList = ArrayList<Job>()
                    for (row in JobSource) {
                        if (row.job.toLowerCase().contains(charSearch.toLowerCase()) || row.company.toLowerCase().contains(charSearch.toLowerCase())) {
                            resultList.add(row)
                        }
                    }
                    jobFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = jobFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                jobFilterList = results?.values as ArrayList<Job>
                notifyDataSetChanged()
            }

        }
    }
}