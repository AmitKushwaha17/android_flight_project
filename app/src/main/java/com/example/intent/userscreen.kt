package com.example.intent

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class userscreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userscreen)
        val bookflight = findViewById<ImageView>(R.id.bookflight)
        val mybooking = findViewById<ImageView>(R.id.mybooking)
        val support = findViewById<ImageView>(R.id.supportservice)
        val logout = findViewById<ImageView>(R.id.logout)
        bookflight.setOnClickListener{
            intent= Intent(this,booking_flight::class.java)
            startActivity(intent)
        }
        logout.setOnClickListener{
            finishAffinity()
        }
        mybooking.setOnClickListener{
            intent = Intent(this,mybookings::class.java)
            startActivity(intent)
        }
        support.setOnClickListener{
            val webpage:Uri = Uri.parse("https://forms.gle/c8JmkCtULgQrkRVW6")
            intent = Intent(Intent.ACTION_VIEW,webpage)
            startActivity(intent)
        }


    }
}