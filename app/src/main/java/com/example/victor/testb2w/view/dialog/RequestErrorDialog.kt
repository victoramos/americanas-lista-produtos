package com.example.victor.testb2w.view.dialog

import android.app.AlertDialog
import android.content.Context
import com.example.victor.testb2w.R

class RequestErrorDialog {
    fun getDialog(context: Context): AlertDialog {
        val dialog = AlertDialog.Builder(context)
                .setTitle(R.string.request_error_title)
                .setMessage(R.string.request_error_body)

        dialog.setPositiveButton(R.string.request_error_positive_button, { dialog1, id ->
            dialog1.dismiss()
        })

        return dialog.create()
    }
}