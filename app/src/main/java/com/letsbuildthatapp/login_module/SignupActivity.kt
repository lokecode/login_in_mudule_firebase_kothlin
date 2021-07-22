package com.letsbuildthatapp.login_module

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        auth = FirebaseAuth.getInstance();


        buttonReg.setOnClickListener {
            val email = editText.text.toString()
            val password = editText2.text.toString()

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password))
                Toast.makeText(this, "გთხოვთ შეავსოთ ყველა ველი", Toast.LENGTH_LONG).show()
            else if (password.length <=5){
                Toast.makeText(this,"პაროლი უნდა შედგებოდეს მინიმუმ 6 სიმბოლოსგან", Toast.LENGTH_LONG).show()
                editText2.setText("") }
            else
                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, OnCompleteListener { task ->
                    if (task.isSuccessful){ Toast.makeText(this,"მომხმარებელი წარმატებით დარეგისტრირდა!", Toast.LENGTH_SHORT).show()}
                    else{ Toast.makeText(this,"რაღაცა ვერ არის კარგად...", Toast.LENGTH_SHORT).show()
                    }
                })
            editText.setText("")
            editText2.setText("")
        }

    }
}