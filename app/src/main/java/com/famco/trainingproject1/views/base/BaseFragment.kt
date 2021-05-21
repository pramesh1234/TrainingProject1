package com.famco.trainingproject1.views.base

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {
    fun showToast(context: Context,message:String){
        Toast.makeText(context, ""+message, Toast.LENGTH_SHORT).show()
    }
    fun showAlertDialog(context: Context,message: String){
        val builder:AlertDialog.Builder=AlertDialog.Builder(context)
        builder.setMessage(message)
        builder.show()
    }
}