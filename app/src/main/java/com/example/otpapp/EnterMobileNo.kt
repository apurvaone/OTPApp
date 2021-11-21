package com.example.otpapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class EnterMobileNo : AppCompatActivity() {

    lateinit var enternumber:EditText
    lateinit var getotpbutton:Button
    lateinit var progressbar:ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entermobileno)

         enternumber= findViewById(R.id.input_mobile_number)
         getotpbutton= findViewById(R.id.getotp)
        progressbar= findViewById(R.id.progressbar_sendotp)


        getotpbutton.setOnClickListener {

            if (!enternumber.text.toString().trim().isEmpty())
            {
                if ((enternumber.text.toString().trim()).length==10){

                    progressbar.visibility=View.VISIBLE
                    getotpbutton.visibility=View.INVISIBLE

                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91"+ enternumber.text.toString(),
                        60,
                        TimeUnit.SECONDS,
                        this,object :PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                                progressbar.visibility=View.GONE
                                getotpbutton.visibility=View.VISIBLE

                            }

                            override fun onVerificationFailed(p0: FirebaseException) {
                                progressbar.visibility=View.GONE
                                getotpbutton.visibility=View.VISIBLE
                                Toast.makeText(this@EnterMobileNo,p0.message,Toast.LENGTH_SHORT).show()

                            }

                            override fun onCodeSent(
                                otpsent: String,
                                p1: PhoneAuthProvider.ForceResendingToken
                            ) {
                                progressbar.visibility=View.GONE
                                getotpbutton.visibility=View.VISIBLE
                                val intent= Intent(applicationContext,VerificationActivity::class.java)
                                  intent.putExtra("mobile",enternumber.text.toString())
                                  intent.putExtra("otpsent",otpsent)
                                 startActivity(intent)

                            }


                        }
                    );

//

                }
                else{
                    Toast.makeText(this,"Please Enter correct number",Toast.LENGTH_LONG).show()
                }
        }
            else
            {
                Toast.makeText(this,"Enter Mobile Number",Toast.LENGTH_SHORT).show()
            }




    }
}




}