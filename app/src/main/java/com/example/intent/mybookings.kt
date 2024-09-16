package com.example.intent

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity


class mybookings : AppCompatActivity() {

    lateinit var  bookingDetailAdapter: BookingDetailAdapter
    lateinit var db : DataBaseHelper
    lateinit var bookingDetailList: ArrayList<BookingDetails>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mybooking_item)
        db= DataBaseHelper(this)

        bookingDetailList = db.getallbookingdetails()

        bookingDetailAdapter=BookingDetailAdapter(this,bookingDetailList)

        val bookingListView = findViewById<ListView>(R.id.mybooks)
        bookingListView.adapter= bookingDetailAdapter
    }
}