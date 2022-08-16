package com.exisdk.utils

import android.app.AlertDialog
import android.content.Context

object DialogAlert {
    fun showAlert(context: Context, isSuccess: Boolean) {
        val message: String
        if (isSuccess == true) {
            message = Constants.SDK_CONNECTION_SUCCESS_ALERT
        } else {
            message = Constants.SDK_CONNECTION_FAILURE_ALERT
        }
        val builder = AlertDialog.Builder(context)
        builder.setTitle(message)
        builder.setNeutralButton(android.R.string.ok) { dialog, which -> }
        builder.show()
    }
}
