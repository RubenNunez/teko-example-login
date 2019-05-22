package com.meow.tekoexamplelogin

import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager

class Helper{

    fun hideKeyboard(view: View, inputMethodManager: InputMethodManager) {
        if (view != null && inputMethodManager != null){
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun isValidUrl(url: String): Boolean {
        val p = Patterns.WEB_URL
        val m = p.matcher(url.toLowerCase())
        return m.matches()
    }
}