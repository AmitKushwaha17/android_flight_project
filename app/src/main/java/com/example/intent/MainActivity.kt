package com.example.intent

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var user: EditText
        lateinit var admins: String
        lateinit var adminp : String
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tv = findViewById<TextView>(R.id.register)
         user = findViewById(R.id.username)
        var pass = findViewById<EditText>(R.id.password)
        val loginb = findViewById<Button>(R.id.login)
        var db = DataBaseHelper(this)
        val adminuser="ADMIN#1708"
        val adminpass="PASSWORDOK"
        tv.setOnClickListener{
            intent = Intent(this,register_page::class.java)
            startActivity(intent)
        }
        loginb.setOnClickListener {
            admins = user.text.toString()
            adminp = pass.text.toString()

            if(admins.equals(adminuser)&&adminp.equals(adminpass)&&adminp.length>=(8))
            {
                intent=Intent(this,adminmain::class.java)
                startActivity(intent)
            }
            else if(admins.isNotEmpty()&&adminp.isNotEmpty()){
                val loginsuccess = db.verifyLogin(admins,adminp)
                if(loginsuccess){
                    Toast.makeText(this,"Login Successful",Toast.LENGTH_SHORT).show()
                    val intent = Intent(this,userscreen::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(this,"Invalid credentials , please try again.",Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(this,"Please enter your login credentials.",Toast.LENGTH_SHORT).show()
            }
            {

            }
        }



    }
}