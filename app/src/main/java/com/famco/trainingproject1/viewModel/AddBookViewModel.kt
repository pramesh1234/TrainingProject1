package com.famco.trainingproject1.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.famco.trainingproject1.RetrofitUtils
import com.famco.trainingproject1.manager.retrofitApi.PostApi
import com.famco.trainingproject1.model.Book
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "AddBookViewModel"

class AddBookViewModel : BaseViewModel() {
val showSuccessToast=MutableLiveData<Boolean>()
    fun hitAddPostApi(title: String, description: String) {

        val book = Book()
        book.body = description
        book.title = title
        book.userId = 1
        val postApi = RetrofitUtils.getRetrofit().create(PostApi::class.java)
        val call: Call<Book> = postApi.setPost(book, "application/json; charset=UTF-8")
        call.enqueue(object : Callback<Book> {
            override fun onResponse(call: Call<Book>, response: Response<Book>) {
                if (response.isSuccessful) {
                    showSuccessToast.value=true
                    Log.e(TAG, "onResponse: " + response.body()?.body)
                }
            }

            override fun onFailure(call: Call<Book>, t: Throwable) {
            }

        })

    }
}