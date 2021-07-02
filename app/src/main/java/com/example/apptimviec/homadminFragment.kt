package com.example.apptimviec

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [homadminFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class homadminFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        val view = inflater.inflate(R.layout.fragment_homadmin, container, false)
        val db = DBSqllite(view.context)

        view.findViewById<Button>(R.id.btnSaveJob).setOnClickListener{
            val textjob = view.findViewById<EditText>(R.id.textAddJob)
            val textcompany = view.findViewById<EditText>(R.id.textAddCompany)
            val textaddress = view.findViewById<EditText>(R.id.textAddAddress)
            val textdetail = view.findViewById<EditText>(R.id.textAddDetail)
            val textsalary = view.findViewById<EditText>(R.id.textAddSalary)
            val textdate = view.findViewById<EditText>(R.id.textAddDate)
            val texturl = view.findViewById<EditText>(R.id.textAddUrl)

            val job = textjob.text.toString()
            val address = textaddress.text.toString()
            val detail = textdetail.text.toString()
            val company = textcompany.text.toString()
            val date = textdate.text.toString()
            val salary = textsalary.text.toString()
            val url = texturl.text.toString()

            val jobb = Job(job,company,address,salary,detail,date,url )
            db.insertDataJob(jobb)
            textjob.setText("")
            textaddress.setText("")
            textdetail.setText("")
            textcompany.setText("")
            textdate.setText("")
            textsalary.setText("")
            texturl.setText("")

            Toast.makeText(view.context, "Thêm thành công!", Toast.LENGTH_LONG).show();
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
         * @return A new instance of fragment homadminFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            homadminFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}