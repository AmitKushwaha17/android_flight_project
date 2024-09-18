package com.example.intent

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class adminmain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_adminmain)
      //  val userimage = findViewById<ImageView>(R.id.imageView)
        val flightimage = findViewById<ImageView>(R.id.imageView2)
        val logoutimage = findViewById<ImageView>(R.id.imageView3)
        //val usertxt = findViewById<TextView>(R.id.Usermanage)
        val flighttxt = findViewById<TextView>(R.id.flightmanage)
        val logouttxt = findViewById<TextView>(R.id.logout)
       // userimage.setOnClickListener {
         //   intent= Intent(this,usermanage::class.java)
           // startActivity(intent)
        //}
//        usertxt.setOnClickListener{
//            intent=Intent(this,usermanage::class.java)
//            startActivity(intent)
//        }
        flightimage.setOnClickListener {
            intent=Intent(this,flightmanage::class.java)
            startActivity(intent)
        }
        flighttxt.setOnClickListener {
            intent=Intent(this,flightmanage::class.java)
            startActivity(intent)
        }
        logoutimage.setOnClickListener {
            finishAffinity()
        }
        logouttxt.setOnClickListener {
            finishAffinity()
        }



    }
}