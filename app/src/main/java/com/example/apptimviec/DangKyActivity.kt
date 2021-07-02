package com.example.apptimviec

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout

class DangKyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dang_ky)
        if(intent != null){
            val db = DBSqllite(this)
            findViewById<Button>(R.id.dk_dk).setOnClickListener {
                val name = findViewById<EditText>(R.id.editTextUsername).text
                val email = findViewById<EditText>(R.id.editTextEmail).text
                val pass = findViewById<EditText>(R.id.editTextPassword).text
                val user = User(name.toString(),email.toString(),pass.toString())
                if (db.checkRegister(name.toString()))
                {
                    db.insertDataUser(user)
                    Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_LONG).show();
                    val handler = Handler()
                    handler.postDelayed({
                        val dangkyIntent = Intent(this, LoginActivity::class.java)
                        startActivity(dangkyIntent)
                    }, 1000)
                }
                else{
                    Toast.makeText(this, "Tên đăng ký đã tồn tại!", Toast.LENGTH_LONG).show();
                }
            }
        }

    }
}