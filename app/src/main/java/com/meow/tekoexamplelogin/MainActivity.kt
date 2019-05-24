package com.meow.tekoexamplelogin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity;

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import kotlinx.android.synthetic.main.content_main.*
import android.content.Intent

class MainActivity : AppCompatActivity() {

    private var username: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Helper Klasse für die Hide Funktionalität in den EditText Feldern
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        val helper = Helper()

        // Event handler for hiding keyboard
        this.loginEditText.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {

                helper.hideKeyboard(v, inputMethodManager)
                // nach der Eingabe Fehler wieder auflösen
                this.loginEditText?.error = null
            }
        }

        this.passwordEditText.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                helper.hideKeyboard(v, inputMethodManager)
                // nach der Eingabe Fehler wieder auflösen
                this.passwordEditText?.error = null
            }
        }


        this.login_btn.setOnClickListener{
            // Handle Login

            val userNameIsValid = this.loginEditText.text.toString() == "ruben"
            val passwordIsValid = this.passwordEditText.text.toString() == "123"


            if (!passwordIsValid){
                this.passwordEditText?.error = "falsches Passwort"
            }
            if (!userNameIsValid){
                this.loginEditText?.error = "falscher Username"
            }

            if(passwordIsValid && userNameIsValid){
                this.username =  this.loginEditText.text.toString()

                // Change Activity
                val intent = Intent(this, Dashboard::class.java)
                intent.putExtra("USERNAME", this.username)
                startActivity(intent)
            }
        }
    }

}
