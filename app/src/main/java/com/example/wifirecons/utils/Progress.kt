package com.example.wifirecons.utils

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.example.wifirecons.R
import com.example.wifirecons.databinding.ItemProgressBinding


class Progress(val context: Context) {

    private lateinit var _dialog: AlertDialog

    fun create(): AlertDialog {
        val customDialog: View = LayoutInflater.from(context).inflate(R.layout.item_progress, null)
        val binding: ItemProgressBinding = ItemProgressBinding.bind(customDialog)
        val alert = AlertDialog.Builder(context)
        alert.setView(binding.root)
        _dialog = alert.create()
        _dialog.setCancelable(false)
        _dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return _dialog
    }

    fun showDialog() {
        _dialog.show()
    }

    fun dismissDialog() {
        _dialog.dismiss()
    }

}