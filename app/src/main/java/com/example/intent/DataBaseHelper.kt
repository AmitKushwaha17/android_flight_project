package com.example.intent

import android.content.ContentValues
import android.content.Context

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import org.intellij.lang.annotations.Identifier
import java.sql.Time


class DataBaseHelper(var context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, 2) {
    companion object {
        private const val DATABASE_NAME = "flightmanage.db"


        //Table flight
        private const val TABLE_FLIGHT = "flight"
        private const val FLIGHT_NO = "fno"
        private const val FLIGHT_NAME = "fname"
        private const val FLIGHT_FROM = "ffrom"
        private const val FLIGHT_TO = "fto"
        private const val FLIGHT_TIME = "ftime"
        private const val FLIGHT_FARE = "ffare"
        private const val FLIGHT_MESSAGE = "fmessage"

        //Table UserLogin
        private const val TABLE_LOGIN = "login"
        private const val USER_ID = "userid"
        private const val USER_USERNAME = "username"
        private const val USER_FIRST_NAME = "userfirstname"
        private const val USER_LAST_NAME = "userlastname"
        private const val USER_PASSWORD = "userpassword"
        private const val USER_MOBILE_NO = "mobile_no"
        private const val USER_EMAIL = "emailaddress"

        //Table Booking
        private const val TABLE_BOOKINGS = "bookings"
        private const val BOOKING_ID = "bookingid"
        private const val BOOKING_FLIGHT_ID = "FlightID"
        private const val BOOKING_USER_ID = "UserID"
        private const val BOOKING_QUANTITY = "Quantity"
        private const val BOOKING_TOTAL = "Total"
        private const val BOOKING_AADHAR = "Aadhar"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createflighttable = """
           CREATE TABLE $TABLE_FLIGHT(
           $FLIGHT_NO  INTEGER PRIMARY KEY ,
           $FLIGHT_NAME TEXT NOT NULL,
           $FLIGHT_FROM TEXT NOT NULL,
           $FLIGHT_TO TEXT NOT NULL,
           $FLIGHT_FARE INTEGER NOT NULL,
           $FLIGHT_TIME TEXT NOT NULL,
           $FLIGHT_MESSAGE TEXT 
           ); """.trimIndent()

        val createuserlogintable = """
           CREATE TABLE $TABLE_LOGIN(
           $USER_ID INTEGER PRIMARY KEY AUTOINCREMENT,
           $USER_USERNAME TEXT UNIQUE NOT NULL,
           $USER_FIRST_NAME TEXT NOT NULL,
           $USER_LAST_NAME TEXT NOT NULL,
           $USER_MOBILE_NO TEXT(13) UNIQUE NOT NULL,
           $USER_EMAIL TEXT  UNIQUE NOT NULL,
           $USER_PASSWORD TEXT NOT NULL
           );
       """.trimIndent()
        //create booking table
        val createbookingtable = """
            CREATE TABLE $TABLE_BOOKINGS(
            $BOOKING_ID INTEGER PRIMARY KEY AUTOINCREMENT,
            $BOOKING_FLIGHT_ID INTEGER NOT NULL,
            $BOOKING_USER_ID INTEGER NOT NULL,
            $BOOKING_QUANTITY INTEGER NOT NULL,
            $BOOKING_TOTAL INTEGER NOT NULL,
            $BOOKING_AADHAR INTEGER ,
            FOREIGN KEY($BOOKING_FLIGHT_ID) REFERENCES $TABLE_FLIGHT($FLIGHT_NO),
             FOREIGN KEY($BOOKING_USER_ID) REFERENCES $TABLE_LOGIN($USER_ID)
            );
        """.trimIndent()
        db?.execSQL(createflighttable)
        db?.execSQL(createuserlogintable)
        db?.execSQL(createbookingtable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_FLIGHT")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_LOGIN")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_BOOKINGS")
        onCreate(db)
    }

    fun insertflight(
        db: SQLiteDatabase,
        ffno: Int,
        fname: String,
        ffrom: String,
        ffto: String,
        ftime: String,
        ffare: Int,
        fmessage: String
    ): Long {
        val values = ContentValues().apply {
            put("fno", ffno)
            put("fname", fname)
            put("ffrom", ffrom)
            put("fto", ffto)
            put("ftime", ftime)
            put("ffare", ffare)
            put("fmessage", fmessage)
        }
        return db.insert("flight", null, values)
    }

    //display all data
    fun readdata(): MutableList<Flight> {
        var flightlist: MutableList<Flight> = ArrayList()
        val db = this.readableDatabase
        val qeury = "Select * from $TABLE_FLIGHT"
        val cursor = db.rawQuery(qeury, null)
        if (cursor.moveToFirst()) {
            do {
                var flight = Flight()
                flight.fno = cursor.getInt(cursor.getColumnIndex(FLIGHT_NO))
                flight.fname = cursor.getString(cursor.getColumnIndex(FLIGHT_NAME))
                flight.ffrom = cursor.getString(cursor.getColumnIndex(FLIGHT_FROM))
                flight.fto = cursor.getString(cursor.getColumnIndex(FLIGHT_TO))
                flight.ftime = cursor.getString(cursor.getColumnIndex(FLIGHT_TIME))
                flight.ffare = cursor.getInt(cursor.getColumnIndex(FLIGHT_FARE))
                flight.fmessage = cursor.getString(cursor.getColumnIndex(FLIGHT_MESSAGE))
                flightlist.add(flight)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return flightlist
    }

    fun updateflight(
        fno: Int, fname: String, ffrom: String, ffto:
        String, ftime: String, ffare: Int, fmessage: String
    ): Boolean {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(FLIGHT_NO, fno)
        cv.put(FLIGHT_NAME, fname)
        cv.put(FLIGHT_FROM, ffrom)
        cv.put(FLIGHT_TO, ffto)
        cv.put(FLIGHT_TIME, ftime)
        cv.put(FLIGHT_FARE, ffare)
        cv.put(FLIGHT_MESSAGE, fmessage)
        val result = db.update(
            TABLE_FLIGHT, cv, FLIGHT_NO +
                    " =? ", arrayOf(fno.toString())
        )
        db.close()
        return result > 0
    }

    fun deleteflightdata(fno: Int) {
        val db = this.writableDatabase
        db.delete(TABLE_FLIGHT, FLIGHT_NO + " =? ", arrayOf(fno.toString()))
        db.close()
    }

    fun registeruserinsert(
        userusername: String,
        userfname: String,
        userlname: String,
        usermnumber: String,
        useremail: String,
        userpass: String
    ): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put("username", userusername)
            put("userfirstname", userfname)
            put("userlastname", userlname)
            put("mobile_no", usermnumber)
            put("emailaddress", useremail)
            put("userpassword", userpass)
        }
        return db.insert(TABLE_LOGIN, null, contentValues)
    }

    fun verifyLogin(identifier: String, password: String): Boolean {
        val db = this.readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM $TABLE_LOGIN WHERE ($USER_USERNAME = ? OR $USER_MOBILE_NO = ? OR $USER_EMAIL = ?) AND " +
                    "$USER_PASSWORD = ?",
            arrayOf(identifier, identifier, identifier, password)
        )
        var userid = -1
        if (cursor.moveToFirst()) {
            userid = cursor.getInt(cursor.getColumnIndexOrThrow("userid"))
        }
        val loginSuccess = cursor.moveToFirst()
        cursor.close()
        return (loginSuccess)
    }

    fun fetchCitiesFromDatabase(): List<String> {
        var cities = mutableListOf<String>()
        val db = this.readableDatabase
        val query = "SELECT DISTINCT $FLIGHT_FROM, $FLIGHT_TO FROM $TABLE_FLIGHT"
        val cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                val flightFrom = cursor.getString(cursor.getColumnIndexOrThrow(FLIGHT_FROM))
                val flightto = cursor.getString(cursor.getColumnIndexOrThrow(FLIGHT_TO))
                if (!cities.contains(flightFrom)) {
                    cities.add(flightFrom)
                }
                if (!cities.contains(flightto)) {
                    cities.add(flightto)
                }
            } while (cursor.moveToNext())
        }
        cursor.close()
        return cities
    }

    fun addBooking(
        flightid: Int,
        userid: Int,
        quantity: Int,
        total: Int,
        aadharno: Int,
    ) {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(BOOKING_FLIGHT_ID, flightid)
            put(BOOKING_USER_ID, userid)
            put(BOOKING_QUANTITY, quantity)
            put(BOOKING_TOTAL, total)
            put(BOOKING_AADHAR, aadharno)
        }
        db.insert(TABLE_BOOKINGS, null, values)
        db.close()
    }

    fun searchFlights(from: String, to: String): MutableList<Flight> {
        val flightList = mutableListOf<Flight>()
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_FLIGHT WHERE $FLIGHT_FROM = ? AND $FLIGHT_TO = ?"
        val cursor = db.rawQuery(query, arrayOf(from, to))
        if (cursor.moveToFirst()) {
            do {
                val flight = Flight(
                    cursor.getInt(cursor.getColumnIndex(FLIGHT_NO)),
                    cursor.getString(cursor.getColumnIndex(FLIGHT_NAME)),
                    cursor.getString(cursor.getColumnIndex(FLIGHT_FROM)),
                    cursor.getString(cursor.getColumnIndex(FLIGHT_TO)),
                    cursor.getString(cursor.getColumnIndex(FLIGHT_TIME)),
                    cursor.getInt(cursor.getColumnIndex(FLIGHT_FARE))
                )
                flightList.add(flight)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return flightList
    }

    fun getallbookingdetails(): ArrayList<BookingDetails> {
        val bookingList = ArrayList<BookingDetails>()
        val db = this.readableDatabase
        val query =
            """
                SELECT * FROM $TABLE_BOOKINGS;
            """
        val cursor = db.rawQuery(query,null)
        if(cursor.moveToFirst()){
            do{
                val bookingDetails = BookingDetails(
                    cursor.getInt(cursor.getColumnIndexOrThrow(BOOKING_ID)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(BOOKING_FLIGHT_ID)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(BOOKING_QUANTITY)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(BOOKING_TOTAL)),
                )
                bookingList.add(bookingDetails)
            }while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return  bookingList
    }

}



