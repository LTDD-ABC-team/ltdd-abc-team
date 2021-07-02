package com.example.apptimviec

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StarFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StarFragment : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_star, container, false)
        val db = DBSqllite(view.context)
        val pre = activity?.getSharedPreferences("userInfo", Context.MODE_PRIVATE)

        if (pre != null) {
            JobSource = db.getJobHistory(pre.getInt("user_id",1))
        }

        val jobRecyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewStar)
        val jobAdapters = JobAdapter(view.context, JobSource)
        jobRecyclerView.layoutManager = GridLayoutManager(view.context,1)
        jobRecyclerView.adapter = jobAdapters

        jobAdapters.onItemClick = { item ->
//                    Toast.makeText(this,article.title,Toast.LENGTH_LONG)
//                        .show()
            val detailIntent = Intent(activity, JobDetailActivity::class.java)
            detailIntent.putExtra("id", item.id)
            detailIntent.putExtra("job", item.job)
            detailIntent.putExtra("company", item.company)
            detailIntent.putExtra("address", item.address)
            detailIntent.putExtra("salary", item.salary)
            detailIntent.putExtra("detail",item.detail)
            detailIntent.putExtra("date", item.date)
            detailIntent.putExtra("url",item.url)
            startActivity(detailIntent)
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
         * @return A new instance of fragment StarFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StarFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}