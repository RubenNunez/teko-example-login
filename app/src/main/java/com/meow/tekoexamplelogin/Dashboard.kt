package com.meow.tekoexamplelogin

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import kotlinx.android.synthetic.main.content_dashboard.*
import android.content.Intent
import android.net.Uri


class Dashboard : AppCompatActivity() {

    private var url : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // beim erstellen der Activity setze ich den URL Öffnen Button auf inaktiv
        // ebenfalls leere ich das URL Feld
        this.url_btn.hide()
        this.urlEditText.text.clear()


        //  Übergabe-Daten auspacken "username"
        val username = intent.getStringExtra("USERNAME")
        this.usernameText.text = username

        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        val helper = Helper()

        // Event handler for hiding keyboard
        this.urlEditText.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                helper.hideKeyboard(v, inputMethodManager)

                // teste eingegebene URL nach fehler
                this.url = this.urlEditText.text.toString()

                // füge Protokoll hinzu, wenn nicht schon vorhanden
                if (!this.url.contains("https://") || !this.url.contains("http://")){
                    this.url = "https://" + this.url
                }

                if (helper.isValidUrl(url)) {

                    // nach der Eingabe Fehler wieder auflösen
                    this.urlEditText?.error = null

                    this.url_btn.show()

                    this.url_btn.setOnClickListener {

                        val browse = Intent(Intent.ACTION_VIEW)
                        browse.data = Uri.parse(this.url)
                        startActivity(Intent.createChooser(browse, "Wähle Browser"))

                    }
                } else {

                    // Wenn Url falsch eingegeben wurde wird ein Fehler angezeigt
                    this.urlEditText?.error = "Falsche URL"
                    this.url_btn.hide()
                }
            }
        }
    }
}
