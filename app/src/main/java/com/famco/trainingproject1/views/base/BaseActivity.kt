package com.famco.trainingproject1.views.base

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class BaseActivity : AppCompatActivity() {

    fun showToast(context: Context,message:String){
        Toast.makeText(context, ""+message, Toast.LENGTH_SHORT).show()
    }
}