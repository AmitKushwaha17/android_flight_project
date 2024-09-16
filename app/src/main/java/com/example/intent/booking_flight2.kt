package com.example.intent

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity


class booking_flight2 : AppCompatActivity() {
    private  lateinit var db : DataBaseHelper

    private lateinit var nameed : EditText
    private lateinit var mobileno : EditText
    private lateinit var quantity : EditText
    private lateinit var adharcard : EditText
    private lateinit var submit : Button
    private lateinit var  flightdetails : TextView
    private  var  flightno : Int = 0
    private var flightfare : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_flight2)
      db = DataBaseHelper(this)
         flightno = intent.getIntExtra("fno",0)
        val flightname = intent.getStringExtra("fname")
        val flighttime = intent.getStringExtra("ftime")
        val flightfrom = intent.getStringExtra("flightfrom")
        val flightto = intent.getStringExtra("flightto")
         flightfare = intent.getIntExtra("ffare",0)

        flightdetails = findViewById(R.id.textflightdetails)
        nameed = findViewById(R.id.name)
        quantity = findViewById(R.id.quantity)
        mobileno = findViewById(R.id.mobileno)
        adharcard =findViewById(R.id.Aadhar_card)
        submit = findViewById(R.id.confirmandpay)
        flightdetails.text = "$flightname ($flightno)\n From : $flightfrom To: $flightto\nTime: $flighttime \nFare: Rs$flightfare "


        submit.setOnClickListener {
            val aadhar = adharcard.text.toString().toInt()
            val q = quantity.text.toString().toInt()
            val totalFare = q * flightfare
            db.addBooking(flightno,1,q,totalFare,aadhar.toString().toInt())
            Toast.makeText(this, "Booking Confirmed", Toast.LENGTH_SHORT).show()
            finish() // Close activity

        }
    }
}