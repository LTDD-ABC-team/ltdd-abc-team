package com.example.apptimviec

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val pre = getSharedPreferences("userInfo", Context.MODE_PRIVATE)
        if(pre.getString("user_name","") != ""){
            val intent = Intent(this,homeActivity::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.btnLogin).setOnClickListener {
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
            finish()
        }

        findViewById<Button>(R.id.btnDangKy).setOnClickListener {
            val dangkyIntent = Intent(this, DangKyActivity::class.java)
            startActivity(dangkyIntent)
            finish()
        }
    }
}