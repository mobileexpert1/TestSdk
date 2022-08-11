package com.exisdk.utils

import android.content.Context
import android.widget.Toast

object ShowMessage {

    fun showMessage(context: Context, message: String, length: Int) {
        Toast.makeText(context, message, length).show()
    }
}
