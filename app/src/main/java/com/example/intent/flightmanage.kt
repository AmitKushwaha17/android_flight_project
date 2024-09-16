package com.example.intent

import android.os.Bundle

import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout


class flightmanage : AppCompatActivity() {

    lateinit var dbHelper: DataBaseHelper

    lateinit var flightno: EditText
    lateinit var flightname2: EditText
    lateinit var fare: EditText
    lateinit var time: EditText
    lateinit var message: EditText
    lateinit var c1: ConstraintLayout
    lateinit var s1: ScrollView
    lateinit var col1: TextView
    lateinit var st : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flightmanage)
        val updates = findViewById<Button>(R.id.update)
        val delete = findViewById<Button>(R.id.delete)
        val submit = findViewById<Button>(R.id.submitupdate)
        val add = findViewById<Button>(R.id.add)
        val search = findViewById<ImageView>(R.id.search)
        var from = findViewById<EditText>(R.id.fromed)
        var to = findViewById<EditText>(R.id.toed)
        col1 = findViewById(R.id.col1)
        flightno = findViewById(R.id.flightno)
        flightname2 = findViewById(R.id.flightname2)
        fare = findViewById(R.id.fare)
        st = "Null"
        time = findViewById(R.id.time)
        message = findViewById(R.id.message)
        c1 = findViewById(R.id.c1)
        s1 = findViewById(R.id.sv1)
        dbHelper = DataBaseHelper(this)
        val db = dbHelper.writableDatabase
        updates.setOnClickListener {
            svvisibiltyoff()
            visibiltyon()
            val flightno = flightno.text.toString()
            val flightname = flightname2.text.toString()
            val flightfrom = from.text.toString()
            val flighto = to.text.toString()
            val flighttime = time.text.toString()
            val flightfare = fare.text.toString()
            val flightmessage = message.text.toString()
            if (flightno.isNotEmpty() && flightname.isNotEmpty() && flightfrom.isNotEmpty() && flighto.isNotEmpty()
                && flighttime.isNotEmpty() && flightfare.isNotEmpty() && flightmessage.isNotEmpty()
            ) {
                val id = flightno.toIntOrNull()
                val fare = flightfare.toInt()
                if (id != null) {
                    val isUpdated = dbHelper.updateflight(
                        id, flightname, flightfrom,
                        flighto, flighttime, fare, flightmessage
                    )
                    if (isUpdated) {
                        clearInputFields()
                        Toast.makeText(this, "Update successfull", Toast.LENGTH_SHORT).show()
                        search.performClick()

                    } else {
                        Toast.makeText(this, "update failed", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(this,"please enter all attributes",Toast.LENGTH_SHORT).show()
                }

            }
        }
        submit.setOnClickListener {

            var fno = flightno.text.toString()
            val fname = flightname2.text.toString()
            val ffrom = from.text.toString().trim()
            val fto = to.text.toString().trim()
            val time = time.text.toString().trim()
            val fare = fare.text.toString()
            val fmessage = st
            if (fname.isEmpty() || fno.isEmpty() || ffrom.isEmpty() || fto.isEmpty() || time.isEmpty() || fare.isEmpty()) {
                Toast.makeText(
                    this,
                    "Please fill up all the fields properly please ",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val fno = fno.toInt()
                val fare = fare.toInt()
                val result = dbHelper.insertflight(db, fno, fname, ffrom, fto, time, fare, fmessage)
                if (result != -1L) {
                    Toast.makeText(this, "Flight Inserted Successfully", Toast.LENGTH_SHORT).show()
                    visibiltyoff()
                    svvisibiltyon()
                    search.performClick()
                    clearInputFields()
                } else {
                    Toast.makeText(this, "Error Inserting FLight ", Toast.LENGTH_SHORT).show()
                }
            }
        }
        add.setOnClickListener {
            svvisibiltyoff()
            visibiltyon()


        }
        search.setOnClickListener {
            svvisibiltyon()
            visibiltyoff()
            var data = dbHelper.readdata()
            col1.text = "FNO : FNAME : FROM : FTO : FTIME : FFARE : FMSG\n"
            for (i in 0..(data.size - 1)) {
                col1.append(
                    data.get(i).fno.toString() + " : " + data.get(i).fname + " : " + data.get(i).ffrom
                            + " : " + data.get(i).fto
                            + " : " + data.get(i).ftime
                            + " : " + data.get(i).ffare +
                             " : " + data.get(i).fmessage +"\n"
                )

            }
        }


        delete.setOnClickListener {

            val id=flightno.text.toString()
            if(id.isNotEmpty())
            {
                val id=id.toInt()
                if(id!=null){
                    val result = dbHelper.deleteflightdata(id)
                    svvisibiltyon()
                    visibiltyoff()
                    search.performClick()
                    Toast.makeText(this,"Data deleted successfully",Toast.LENGTH_SHORT).show()
                }
                else
                {
                    Toast.makeText(this,"Data Not Found",Toast.LENGTH_SHORT).show()
                }
            }

        }

    }

    private fun visibiltyon() {
        if (c1.visibility == ConstraintLayout.GONE) {
            c1.visibility = ConstraintLayout.VISIBLE
        }
    }

    private fun visibiltyoff() {
        if (c1.visibility == ConstraintLayout.VISIBLE) {
            c1.visibility = ConstraintLayout.GONE
        }
    }

    private fun svvisibiltyon() {
        if (s1.visibility == ScrollView.GONE) {
            s1.visibility = ScrollView.VISIBLE
        }
    }

    private fun svvisibiltyoff() {
        if (s1.visibility == ScrollView.VISIBLE) {
            s1.visibility = ScrollView.GONE
        }
    }

    private fun clearInputFields(vararg editTexts: EditText) {
        for (editText in editTexts) {
            editText.text.clear()
        }
    }

    private fun displayFlightList(data: List<Flight>, textView: TextView) {
        val builder = StringBuilder()
        for (flight in data) {

            builder.append("Flight No: ${flight.fno}\n")
            builder.append("Flight Name: ${flight.fname}\n")
            builder.append("From: ${flight.ffrom}\n")
            builder.append("To: ${flight.fto}\n")
            builder.append("Fare: ${flight.ffare}\n")
            builder.append("Time: ${flight.ftime}\n")
            builder.append("\n--------------------\n\n")
        }
        textView
    }

}