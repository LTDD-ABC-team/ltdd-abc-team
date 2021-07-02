package com.example.apptimviec

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [homeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class homeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var JobSource = ArrayList<Job>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val db = DBSqllite(view.context)

        JobSource = db.readDataJob()

        val jobRecyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewhome)
        val jobAdapter = JobAdapter(view.context, JobSource)

        jobRecyclerView.layoutManager = GridLayoutManager(view.context,1)
        jobRecyclerView.adapter = jobAdapter

        val find = view.findViewById<SearchView>(R.id.searchView)
        find.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                jobAdapter.getFilter().filter(newText)

                return false
            }
        })

        jobAdapter.onItemClick = {item ->
//                    Toast.makeText(this,article.title,Toast.LENGTH_LONG)
//                        .show()
            val detailIntent = Intent(activity,JobDetailActivity::class.java)
            detailIntent.putExtra("id",item.id.toString())
            detailIntent.putExtra("job",item.job)
            detailIntent.putExtra("company",item.company)
            detailIntent.putExtra("address",item.address)
            detailIntent.putExtra("salary",item.salary)
            detailIntent.putExtra("detail",item.detail)
            detailIntent.putExtra("date",item.date)
            detailIntent.putExtra("url",item.url)
            startActivity(detailIntent)
/*
           val frag = JobDetailFragment.newInstance(item.job,item.address,item.detail)
            val trans = activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.fragmentHolder,frag)
                addToBackStack(null)
                commit()
            }*/

        }
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment homeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            homeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}