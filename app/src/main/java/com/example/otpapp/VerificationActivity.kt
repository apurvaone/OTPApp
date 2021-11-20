package com.example.otpapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class VerificationActivity : AppCompatActivity() {

    lateinit var input1:EditText
    lateinit var input2:EditText
    lateinit var input3:EditText
    lateinit var input4:EditText
    lateinit var input5:EditText
    lateinit var input6:EditText

    lateinit var verifyButton:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification)

        input1= findViewById(R.id.inputOTP1)
        input2= findViewById(R.id.inputOTP2)
        input3= findViewById(R.id.inputOTP3)
        input4= findViewById(R.id.inputOTP4)
        input5= findViewById(R.id.inputOTP5)
        input6= findViewById(R.id.inputOTP6)
        verifyButton= findViewById(R.id.submitButton)

        var textView:TextView= findViewById(R.id.textMobileShow)

        textView.text= String.format("+91- %s",intent.getStringExtra("mobile"))


        verifyButton.setOnClickListener {
            if (!input1.text.toString().trim().isEmpty() &&!input2.text.toString().trim().isEmpty() &&!input3.text.toString().trim().isEmpty() &&!input4.text.toString().trim().isEmpty()&& !input5.text.toString().trim().isEmpty()&&!input6.text.toString().trim().isEmpty())
            {
                Toast.makeText(applicationContext,"Otp verify",Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(applicationContext,"Please enter all number",Toast.LENGTH_SHORT).show()
            }
        }






    }
}