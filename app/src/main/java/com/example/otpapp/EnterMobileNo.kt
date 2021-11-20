package com.example.otpapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class EnterMobileNo : AppCompatActivity() {

    lateinit var enternumber:EditText
    lateinit var getotpbutton:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entermobileno)

         enternumber= findViewById(R.id.input_mobile_number)
         getotpbutton= findViewById(R.id.getotp)


        getotpbutton.setOnClickListener {

            if (!enternumber.text.toString().trim().isEmpty())
            {
                if ((enternumber.text.toString().trim()).length==10){
                        val intent= Intent(applicationContext,VerificationActivity::class.java)
                intent.putExtra("mobile",enternumber.text.toString())
                startActivity(intent)

                }
                else{
                    Toast.makeText(this,"Please Enter correct number",Toast.LENGTH_LONG)
                }
        }
            else
            {
                Toast.makeText(this,"Enter Mobile Number",Toast.LENGTH_SHORT).show()
            }


    }
}}