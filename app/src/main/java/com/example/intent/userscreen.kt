package com.example.intent

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2

class userscreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userscreen)
        val bookflight = findViewById<ImageView>(R.id.bookflight)
        val mybooking = findViewById<ImageView>(R.id.mybooking)
        val support = findViewById<ImageView>(R.id.supportservice)
        val logout = findViewById<ImageView>(R.id.logout)

        val images = listOf(
            R.drawable.qoute1,
            R.drawable.quotes2,
            R.drawable.quotes3,
            R.drawable.quates4,
            R.drawable.quotes5
        )

        val viewPager: ViewPager2 = findViewById(R.id.viewPager)
        viewPager.adapter = ImageSliderAdapter(images)

        autoScrollViewPager(viewPager,images.size)
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
    private fun autoScrollViewPager(viewPager:ViewPager2,itemCount:Int){
        val handler = Handler(Looper.getMainLooper())
        var currentPage = 0

        val runnable = object : Runnable {
            override fun run() {
                if (currentPage == itemCount) {
                    currentPage = 0
                }
                viewPager.setCurrentItem(currentPage++, true)
                handler.postDelayed(this, 3000)
            }
        }
        handler.post(runnable)
    }
}