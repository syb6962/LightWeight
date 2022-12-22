package com.example.lightweight

import android.content.Context
import android.util.Log
import android.widget.Toast

const val test3 = 3

fun Log.showLog(tag: String, message: String) {
    Log.i(tag, message)
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}