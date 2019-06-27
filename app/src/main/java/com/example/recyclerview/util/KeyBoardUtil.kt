package com.example.recyclerview.util

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

class KeyBoardUtil {
    companion object{
        fun hideKeyBoard(context: Context, view: View){
            val manager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            manager.hideSoftInputFromWindow(view.windowToken,0)
        }
    }
}