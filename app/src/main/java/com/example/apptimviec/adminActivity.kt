package com.example.apptimviec

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class adminActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)



        val btn1 = findViewById<ImageButton>(R.id.imgbtnHome2)
        val btn2 = findViewById<ImageButton>(R.id.imgbtnHome3)
        val btn3 = findViewById<ImageButton>(R.id.imgbtnPerson2)


        val fragment1 = homadminFragment()
        val fragment2 = homeFragment()
        val fragment3 = logoutAdminFragment()

        supportFragmentManager.beginTransaction().apply {
            add(R.id.fragmentHolderAdmin, fragment1)
            addToBackStack(null)
            btn1.setBackgroundColor(Color.rgb(235,235,235))
            btn2.setBackgroundColor(Color.rgb(255, 255, 255))
            btn3.setBackgroundColor(Color.rgb(255, 255, 255))

            commit()
        }

        btn1.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentHolderAdmin, fragment1)
                addToBackStack(null)
                btn1.setBackgroundColor(Color.rgb(235,235,235))
                btn2.setBackgroundColor(Color.rgb(255, 255, 255))
                btn3.setBackgroundColor(Color.rgb(255, 255, 255))

                commit()
            }
        }

        btn2.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentHolderAdmin, fragment2)
                addToBackStack(null)
                btn2.setBackgroundColor(Color.rgb(235,235,235))
                btn1.setBackgroundColor(Color.rgb(255, 255, 255))
                btn3.setBackgroundColor(Color.rgb(255, 255, 255))

                commit()
            }
        }

        btn3.setOnClickListener{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentHolderAdmin,fragment3)
                addToBackStack(null)
                btn3.setBackgroundColor(Color.rgb(235,235,235))
                btn1.setBackgroundColor(Color.rgb(255, 255, 255))
                btn2.setBackgroundColor(Color.rgb(255, 255, 255))

                commit()
            }
        }


    }
}