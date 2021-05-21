package com.famco.trainingproject1.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.famco.trainingproject1.RetrofitUtils
import com.famco.trainingproject1.manager.retrofitApi.PostApi
import com.famco.trainingproject1.model.Book
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "EditBookDialogViewModel"

class EditBookDialogViewModel : BaseViewModel() {
    val bookData: MutableLiveData<Book> by lazy {
        MutableLiveData<Book>()
    }
    private val postApi: PostApi = RetrofitUtils.getRetrofit().create(PostApi::class.java)
    fun hitGetPostApi(id: Int) {
        var book: Book? = Book()
        val call: Call<Book> = postApi.getPost(id)
        call.enqueue(object : Callback<Book> {
            override fun onResponse(call: Call<Book>, response: Response<Book>) {
                book = response.body()!!
                bookData.value = book
            }

            override fun onFailure(call: Call<Book>, t: Throwable) {
            }

        })
    }

    fun hitUpdatePostApi(id: Int?, book: Book) {
        val call: Call<Book>? =
            id?.let { postApi.getUpdatePost(it, book, "application/json; charset=UTF-8") }
        call?.enqueue(object : Callback<Book> {
            override fun onResponse(call: Call<Book>, response: Response<Book>) {
                if (response.isSuccessful) {
                    Log.e(TAG, "onResponse: successfully updated")
                }
            }

            override fun onFailure(call: Call<Book>, t: Throwable) {
            }

        })

    }
}