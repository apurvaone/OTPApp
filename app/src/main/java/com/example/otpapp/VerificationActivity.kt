package com.example.otpapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextWatcher
import android.text.Editable
import android.view.View
import android.widget.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit


class VerificationActivity : AppCompatActivity() {
    lateinit var otpsent:String
    lateinit var progressbar: ProgressBar

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
        progressbar= findViewById(R.id.progressbar_verifyotp)


        otpsent= intent.getStringExtra("otpsent")!!

        var textView:TextView= findViewById(R.id.textMobileShow)

        textView.text= String.format("+91- %s",intent.getStringExtra("mobile"))


        verifyButton.setOnClickListener {

            if (!input1.text.toString().trim().isEmpty() &&!input2.text.toString().trim().isEmpty() &&!input3.text.toString().trim().isEmpty() &&!input4.text.toString().trim().isEmpty()&& !input5.text.toString().trim().isEmpty()&&!input6.text.toString().trim().isEmpty())
            {
                var enteredOTP:String= input1.text.toString()+input2.text.toString()+input3.text.toString()+input4.text.toString()+input5.text.toString()+input6.text.toString()
                var phoneAuthCredential= PhoneAuthProvider.getCredential(otpsent,enteredOTP)

                FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                    .addOnCompleteListener(object : OnCompleteListener<AuthResult> {
                        override fun onComplete(p0: Task<AuthResult>) {

                            progressbar.visibility= View.VISIBLE
                            verifyButton.visibility= View.INVISIBLE
                            if (p0.isSuccessful())
                            {
                                val intent:Intent= Intent(this@VerificationActivity,Dashboard::class.java)
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK )
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)

                                startActivity(intent)

                            }
                            else
                            {
                                Toast.makeText(this@VerificationActivity,"Enter the correct otp",Toast.LENGTH_SHORT).show()
                            }

                        }


                    })

                Toast.makeText(applicationContext,"Otp verify",Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(applicationContext,"Please enter all number",Toast.LENGTH_SHORT).show()
            }

        }
        numberotpmove()

        findViewById<TextView>(R.id.resend_otp).setOnClickListener {

            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91"+ intent.getStringExtra("mobile"),
                60,
                TimeUnit.SECONDS,
                this,object :PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                    override fun onVerificationCompleted(p0: PhoneAuthCredential) {


                    }

                    override fun onVerificationFailed(p0: FirebaseException) {

                        Toast.makeText(this@VerificationActivity,p0.message,Toast.LENGTH_SHORT).show()

                    }

                    override fun onCodeSent(
                        newotpsent: String,
                        p1: PhoneAuthProvider.ForceResendingToken
                    ) {
                        otpsent=newotpsent
                        Toast.makeText(this@VerificationActivity,"New otp sent",Toast.LENGTH_SHORT).show()

                    }


                }
            );
        }









    }

    private fun numberotpmove() {
        input1.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                if(!p0.toString().trim().isEmpty()){
                    input2.requestFocus()
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        });
        input2.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                if(!p0.toString().trim().isEmpty()){
                    input3.requestFocus()
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        });

        input3.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                if(!p0.toString().trim().isEmpty()){
                    input4.requestFocus()
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        });

        input4.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                if(!p0.toString().trim().isEmpty()){
                    input5.requestFocus()
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        });
        input5.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                if(!p0.toString().trim().isEmpty()){
                    input6.requestFocus()
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        });


    }
}