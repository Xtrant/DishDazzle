package com.dundxn.dishdazzle.utils

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.material.progressindicator.LinearProgressIndicator

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun showLoading(isLoading: Boolean, item: ProgressBar) {
    item.visibility = if (isLoading) View.VISIBLE else View.GONE
}

