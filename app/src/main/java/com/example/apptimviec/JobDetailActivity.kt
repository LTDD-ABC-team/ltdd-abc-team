package com.example.apptimviec

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity

class JobDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_detail)

        /*assert(
            supportActionBar != null //null check
        )
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        */

        if(intent != null){
            findViewById<TextView>(R.id.textViewJob).text = intent.getStringExtra("job")
            findViewById<TextView>(R.id.textViewCompany).text = intent.getStringExtra("company")
            findViewById<TextView>(R.id.textViewAddress).text = intent.getStringExtra("address")
            findViewById<TextView>(R.id.textViewSalary).text = intent.getStringExtra("salary")
            findViewById<TextView>(R.id.textViewDetail).text = intent.getStringExtra("detail")
            var myTextView = findViewById<TextView>(R.id.textViewDetail)
            myTextView.movementMethod = ScrollingMovementMethod()

            findViewById<TextView>(R.id.textViewDate).text = intent.getStringExtra("date")

            val db = DBSqllite(this)
            val pre = getSharedPreferences("userInfo", Context.MODE_PRIVATE)
            val btn = findViewById<ToggleButton>(R.id.toggleButtonStar2)
            val btnWeb = findViewById<Button>(R.id.btnUngTuyen)
            btnWeb.setOnClickListener{
                val intentd = Intent(this,WebActivity::class.java)
                intentd.putExtra("url",intent.getStringExtra("url").toString())

                startActivity(intentd)
            }

            if(db.checkHistory(intent.getStringExtra("id")!!.toInt(),pre.getInt("user_id",1)))
            {
                btn.setChecked(true)
            }

            btn.setOnClickListener {
                if (btn.isChecked) {
                    if (pre != null) {
                        db.insertDataHistory(intent.getStringExtra("id")!!.toInt(), pre.getInt("user_id", 1))
                        Toast.makeText(this, "Đã lưu!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    if (pre != null) {
                        db.deleteDataHistory(intent.getStringExtra("id")!!.toInt(), pre.getInt("user_id", 1))
                        Toast.makeText(this, "Hủy lưu!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }
}