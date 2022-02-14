package com.example.otp

import android.app.PendingIntent.getActivity
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class entermobilenumber:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entermobilenumber)
        val enternumber: EditText = findViewById(R.id.numberhint)
        val getotpbutton: Button = findViewById(R.id.sendotp)
        val progressbar: ProgressBar = findViewById(R.id.progress_sending_otp)

        getotpbutton.setOnClickListener{
            if (!enternumber.text.toString().trim().isEmpty()){
                if ((enternumber.text.toString().trim()).length == 10){
                    progressbar.visibility = View.VISIBLE
                    getotpbutton.visibility = View.INVISIBLE
                    val mCallBacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                        override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                            progressbar.visibility =View.GONE
                            getotpbutton.visibility = View.VISIBLE }

                        override fun onVerificationFailed(p0: FirebaseException) {
                            progressbar.visibility =View.GONE
                            getotpbutton.visibility = View.VISIBLE
                            Log.w(TAG, "onVerificationFailed", p0)

                            if (p0 is FirebaseAuthInvalidCredentialsException) {
                                // Invalid request
                            } else if (p0 is FirebaseTooManyRequestsException) {
                                // The SMS quota for the project has been exceeded
                            }
                        }
                        override fun onCodeSent(backendotp: String , p1: PhoneAuthProvider.ForceResendingToken ) {
                            progressbar.visibility =View.GONE
                            getotpbutton.visibility = View.VISIBLE
                            val intent = Intent(this@entermobilenumber,otpverification::class.java)
                            intent.putExtra("mobile",enternumber.text.toString())
                            intent.putExtra("backendotp",backendotp)
                            startActivity(intent)
                        }
                    }
                        val phoneAuth: PhoneAuthProvider = PhoneAuthProvider.getInstance()
                    phoneAuth.verifyPhoneNumber(
                        "+91" + enternumber.text.toString(),
                        60, TimeUnit.SECONDS,this,mCallBacks)
                    }
                else{
                    Toast.makeText(this@entermobilenumber,"Please enter correct number",Toast.LENGTH_SHORT).show()
                }
                }
            else{Toast.makeText(this@entermobilenumber,"Enter Mobile number",Toast.LENGTH_SHORT).show()}
                }
       }

}


