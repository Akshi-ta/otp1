package com.example.otp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.core.widget.addTextChangedListener
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

class otpverification : AppCompatActivity() {
         var getotpbackend: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otpverification)
        val textView1: TextView = findViewById(R.id.inputotp1)
        val textView2: TextView = findViewById(R.id.inputotp2)
        val textView3: TextView = findViewById(R.id.inputotp3)
        val textView4: TextView = findViewById(R.id.inputotp4)
        val textView5: TextView = findViewById(R.id.inputotp5)
        val textView6: TextView = findViewById(R.id.inputotp6)
        val textView: TextView = findViewById(R.id.samenumber)

        textView.setText(String.format("+91-%s",intent.getStringExtra("mobile")))
        getotpbackend = intent.getStringExtra("backendotp")

        val progressBarverifyotp : ProgressBar = findViewById(R.id.progress_verify_otp)
        val verifybuttonclick : Button = findViewById(R.id.buttongetotp)
        verifybuttonclick.setOnClickListener{

            if (!textView1.text.toString().trim().isEmpty() && (!textView2.text.toString().trim().isEmpty())&& (!textView3.text.toString().trim().isEmpty()) && (!textView4.text.toString().trim().isEmpty()) && (!textView5.text.toString().trim().isEmpty()) && (!textView6.text.toString().trim().isEmpty())){
                var entercodeotp: String  = textView1.text.toString()+
                    textView2.text.toString() +
                    textView3.text.toString() +
                    textView4.text.toString() +
                    textView5.text.toString() +
                    textView6.text.toString()
                if (getotpbackend!=null){
                    progressBarverifyotp.visibility = View.VISIBLE
                    verifybuttonclick.visibility = View.INVISIBLE
                    val phoneAuthCredential: PhoneAuthCredential = PhoneAuthProvider.getCredential(
                    getotpbackend.toString(), entercodeotp
                    )
                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                        .addOnCompleteListener{
                            progressBarverifyotp.visibility = View.GONE
                            verifybuttonclick.visibility = View.VISIBLE

                            if(it.isSuccessful()){
                                val intent : Intent = Intent(this,dashboard::class.java)
                                intent.flags = (Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                startActivity(intent)
                            }
                            else{
                                Toast.makeText(this@otpverification , "Enter the correct otp",Toast.LENGTH_SHORT).show()
                            }
                        }
                }
            }

//                Toast.makeText(this@otpverification,"otp verify",Toast.LENGTH_SHORT).show()
            else{
                Toast.makeText(this@otpverification,"please enter all number",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun numberotpmove(){
        val textView1: TextView = findViewById(R.id.inputotp1)
        val textView2: TextView = findViewById(R.id.inputotp2)
        val textView3: TextView = findViewById(R.id.inputotp3)
        val textView4: TextView = findViewById(R.id.inputotp4)
        val textView5: TextView = findViewById(R.id.inputotp5)
        val textView6: TextView = findViewById(R.id.inputotp6)
        val textView: TextView = findViewById(R.id.samenumber)
        textView1.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence? , start: Int , count: Int , after: Int) {}
            override fun onTextChanged(s: CharSequence? , start: Int , before: Int , count: Int) {
                if (!s.toString().trim().isEmpty()){
                    textView2.requestFocus()

                }
            }
        })
        textView2.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence? , start: Int , count: Int , after: Int) {}
            override fun onTextChanged(s: CharSequence? , start: Int , before: Int , count: Int) {
                if (!s.toString().trim().isEmpty()){
                    textView3.requestFocus()

                }
            }
        })
        textView3.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence? , start: Int , count: Int , after: Int) {}
            override fun onTextChanged(s: CharSequence? , start: Int , before: Int , count: Int) {
                if (!s.toString().trim().isEmpty()){
                    textView4.requestFocus()

                }
            }
        })
        textView4.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence? , start: Int , count: Int , after: Int) {}
            override fun onTextChanged(s: CharSequence? , start: Int , before: Int , count: Int) {
                if (!s.toString().trim().isEmpty()){
                    textView5.requestFocus()

                }
            }
        })
        textView5.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence? , start: Int , count: Int , after: Int) {}
            override fun onTextChanged(s: CharSequence? , start: Int , before: Int , count: Int) {
                if (!s.toString().trim().isEmpty()){
                    textView6.requestFocus()

                }
            }
        })
        }

    }


