package com.example.apptimviec

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class homeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        var pre = getSharedPreferences("userInfo", Context.MODE_PRIVATE)
        if(pre.getString("user_name","") == ""){
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }

        val btn1 = findViewById<ImageButton>(R.id.imgbtnHome)
        val btn2 = findViewById<ImageButton>(R.id.imgbtnStar)
        val btn3 = findViewById<ImageButton>(R.id.imgbtnPerson)
        //val btn4 = findViewById<ImageButton>(R.id.imgbtnNew)

        val fragment1 = homeFragment()
        val fragment2 = StarFragment()
        val fragment3 = personFragment()
        //val fragment4 = newFragment()
        supportFragmentManager.beginTransaction().apply {
            add(R.id.fragmentHolder, fragment1)
            addToBackStack(null)
            btn1.setBackgroundColor(Color.rgb(235,235,235))
            btn2.setBackgroundColor(Color.rgb(255,255,255))
            btn3.setBackgroundColor(Color.rgb(255,255,255))
            //btn4.setBackgroundColor(Color.rgb(255,255,255))
            commit()
        }
        btn1.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentHolder, fragment1)
                addToBackStack(null)
                btn1.setBackgroundColor(Color.rgb(235,235,235))
                btn2.setBackgroundColor(Color.rgb(255,255,255))
                btn3.setBackgroundColor(Color.rgb(255,255,255))
                //btn4.setBackgroundColor(Color.rgb(255,255,255))
                commit()
            }
        }

        btn2.setOnClickListener{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentHolder,fragment2)
                addToBackStack(null)
                btn2.setBackgroundColor(Color.rgb(235,235,235))
                btn1.setBackgroundColor(Color.rgb(255,255,255))
                btn3.setBackgroundColor(Color.rgb(255,255,255))
                //btn4.setBackgroundColor(Color.rgb(255,255,255))
                commit()
            }
        }

        btn3.setOnClickListener{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentHolder,fragment3)
                addToBackStack(null)
                btn3.setBackgroundColor(Color.rgb(235,235,235))
                btn1.setBackgroundColor(Color.rgb(255,255,255))
                btn2.setBackgroundColor(Color.rgb(255,255,255))
                //btn4.setBackgroundColor(Color.rgb(255,255,255))
                commit()
            }
        }

        /*btn4.setOnClickListener{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentHolder,fragment4)
                addToBackStack(null)
                btn4.setBackgroundColor(Color.rgb(235,235,235))
                btn1.setBackgroundColor(Color.rgb(255,255,255))
                btn2.setBackgroundColor(Color.rgb(255,255,255))
                btn3.setBackgroundColor(Color.rgb(255,255,255))
                commit()
            }
        }*/
    }
}