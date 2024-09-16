package com.example.intent

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class BookingDetailAdapter (private val context:Context,private val bookingDetailList: ArrayList<BookingDetails>):BaseAdapter() {
    override fun getCount(): Int {
        return bookingDetailList.size
    }

    override fun getItem(position: Int): Any {
        return bookingDetailList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        if (view == null) {
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.activity_mybookings, parent, false)
        }
        val bookingIdText = view?.findViewById<TextView>(R.id.bookingid)
        val flightIdTextView = view?.findViewById<TextView>(R.id.flightid)
        val quantityTextView = view?.findViewById<TextView>(R.id.quantity)
        val totalTextView = view?.findViewById<TextView>(R.id.total)
        val bookingDetails = bookingDetailList[position]
        bookingIdText?.text = "${bookingDetails.bookingId}"
        flightIdTextView?.text = "${bookingDetails.flightId}"
        quantityTextView?.text = "${bookingDetails.quantity}"
        totalTextView?.text = "${bookingDetails.total}"

        return  view!!
    }

}