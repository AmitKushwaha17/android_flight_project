package com.example.intent

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity


class register_page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       var db = DataBaseHelper(this)
        setContentView(R.layout.activity_register_page)
        var username = findViewById<EditText>(R.id.username)
        var firstname = findViewById<EditText>(R.id.firstname)
        var lastname = findViewById<EditText>(R.id.lastname)
        var mobileno = findViewById<EditText>(R.id.mobile)
        var emailaddress = findViewById<EditText>(R.id.emailaddress)
        var password = findViewById<EditText>(R.id.password)
        var retype = findViewById<EditText>(R.id.retype)
        val register = findViewById<Button>(R.id.register)

        //register button
        register.setOnClickListener{
            val username = username.text.toString()
            val firstname = firstname.text.toString()
            val lastname = lastname.text.toString()
            var mobileno = mobileno.text.toString()
            val emailadress = emailaddress.text.toString()
            val password = password.text.toString()
            val retype = retype.text.toString()
            if(username.isEmpty()||firstname.isEmpty()||lastname.isEmpty()||mobileno.isEmpty()||emailadress.isEmpty()||!password.equals(retype)||password.length<8){
                Toast.makeText(this,"Please fill up all the fields properly please",Toast.LENGTH_SHORT).show()
            }else{
                val result = db.registeruserinsert(username,firstname,lastname,mobileno,emailadress,password)
                if (result != -1L){
                    Toast.makeText(this,"User Information Inserted Successfully",Toast.LENGTH_SHORT).show()
                    intent_tologin()
                    clearInputFields()
                }
                else{
                    Toast.makeText(this,"error : check your data carefully , and ply try again ",Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
    private fun intent_tologin(){
        intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
    private fun clearInputFields(vararg editTexts: EditText) {
        for (editText in editTexts) {
            editText.text.clear()
        }
    }
}