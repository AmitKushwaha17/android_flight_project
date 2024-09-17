package com.example.intent

import FlightAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.*
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class booking_flight : AppCompatActivity() {

    private lateinit var db : DataBaseHelper
    private lateinit var flightAdapter: FlightAdapter
    private lateinit var flightListView: ListView
    private var flightList = mutableListOf<Flight>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_flight)
         db = DataBaseHelper(this)
        val edfrom = findViewById<AutoCompleteTextView>(R.id.from)
        val edto = findViewById<AutoCompleteTextView>(R.id.to)
        val search = findViewById<Button>(R.id.Search)
        flightListView =findViewById(R.id.chart)


        //font


        val cities = db.fetchCitiesFromDatabase()
        val adapter = ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,cities)
        edfrom.setAdapter(adapter)
        edto.setAdapter(adapter)
        search.setOnClickListener{
            val flightfrom =edfrom.text.toString()
            val flightto = edto.text.toString()
            if(flightfrom.isEmpty() || flightto.isEmpty()){
                Toast.makeText(this,"Please select both Flight from and to cities ",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"Searching flights from $flightfrom to $flightto",Toast.LENGTH_SHORT).show()
                flightList =db.searchFlights(flightfrom,flightto)
               flightAdapter = FlightAdapter(this,flightList)
               flightListView.adapter = flightAdapter
            }
        }
       flightListView.setOnItemClickListener() { _, _, position, _ ->
           val selectedFlight = flightList[position]
           val intent = Intent(this, booking_flight2::class.java).apply{
           putExtra("fno", selectedFlight.fno)
               putExtra("flightfrom", selectedFlight.ffrom)
               putExtra("flightto", selectedFlight.fto)
           putExtra("fname", selectedFlight.fname)
           putExtra("ftime", selectedFlight.ftime)
           putExtra("ffare", selectedFlight.ffare)

       }
            startActivity(intent)
        }
    }

}