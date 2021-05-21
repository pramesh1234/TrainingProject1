package com.famco.trainingproject1.viewModel

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    fun showToast(application: Context,message:String){
        Toast.makeText(application, message, Toast.LENGTH_SHORT).show()
    }
}