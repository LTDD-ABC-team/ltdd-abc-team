package com.example.apptimviec

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val pre = getSharedPreferences("userInfo", Context.MODE_PRIVATE)
        if(pre.getString("user_name","") != ""){
            val intent = Intent(this,homeActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnLogin_dangky).setOnClickListener {
            val dangkyIntent = Intent(this, DangKyActivity::class.java)
            startActivity(dangkyIntent)
        }

        findViewById<Button>(R.id.btnLogin_login).setOnClickListener {
            val db = DBSqllite(this)
            val name = findViewById<EditText>(R.id.editLogin_username).text.toString()
            val pass = findViewById<EditText>(R.id.editLogin_pwd).text.toString()
            val ck = db.checkLogin(name,pass)
            if (name == "admin" && pass == "admin")
            {
                var sharedPre = getSharedPreferences("userInfo", Context.MODE_PRIVATE)
                var edit = sharedPre.edit()
                edit.putInt("user_id",-1)
                edit.commit()
                val loginIntent = Intent(this, adminActivity::class.java)
                startActivity(loginIntent)
                finish()
            }
            else{
                if(ck != -1)
                {
                    var sharedPre = getSharedPreferences("userInfo", Context.MODE_PRIVATE)
                    var edit = sharedPre.edit()
                    edit.putString("user_name",name)
                    edit.putInt("user_id",ck)
                    edit.commit()
                    val loginIntent = Intent(this, homeActivity::class.java)
                    startActivity(loginIntent)
                    finish()

                }
                else {
                    Toast.makeText(this, "Sai tên đăng nhập hoặc mật khẩu!", Toast.LENGTH_LONG).show();
                }
            }


        }
    }
}