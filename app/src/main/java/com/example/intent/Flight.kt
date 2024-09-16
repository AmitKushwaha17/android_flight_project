package com.example.intent

import java.lang.reflect.Constructor

class Flight{
    var fno : Int = 0
    var fname : String = ""
    var ffrom : String = ""
    var fto : String =""
    var ftime : String =""
    var ffare : Int = 0
    var fmessage : String = ""
    constructor(fno:Int,fname:String,ffrom:String,fto:String,ftime:String,ffare:Int,fmessage:String){
        this.fno=fno
        this.ffare=ffare
        this.ffrom=ffrom
        this.fto=fto
        this.fname=fname
        this.ftime=ftime
        this.fmessage=fmessage

    }
    constructor(fno:Int,fname:String,ffrom:String,fto:String,ftime:String,ffare:Int){
        this.fno=fno
        this.ffare=ffare
        this.ffrom=ffrom
        this.fto=fto
        this.fname=fname
        this.ftime=ftime
    }
    var userid:Int = 0
    var username:String = ""
    var userfirstname:String = ""
    var userlastname:String = ""
    var userpassword: String =""
    var mobile_no:String =""
    var emailaddress:String=""
    constructor(userid:Int,username:String,userfirstname:String,userlastname:String,mobile_no:String,emailaddress:String,userpassword:String)
    {
        this.userid = userid
        this.username=username
        this.userfirstname=userfirstname
        this.userlastname=userlastname
        this.userpassword=userpassword
        this.mobile_no=mobile_no
        this.emailaddress=emailaddress
    }

    constructor(){}
}
data class BookingDetails(
    val bookingId:Int,
    val flightId:Int,
    val quantity : Int,
    val total: Int
)