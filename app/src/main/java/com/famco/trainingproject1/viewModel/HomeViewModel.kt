package com.famco.trainingproject1.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.famco.trainingproject1.RetrofitUtils
import com.famco.trainingproject1.manager.retrofitApi.PostApi
import com.famco.trainingproject1.model.Book
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "HomeViewModel"

class HomeViewModel : BaseViewModel() {
    val isPostDeleted = MutableLiveData<Boolean>()
    val bookList: MutableLiveData<List<Book>> = MutableLiveData<List<Book>>()
    private val postApi: PostApi = RetrofitUtils.getRetrofit().create(PostApi::class.java)


    init {
        val call: Call<List<Book>> = postApi.getPosts()
        call.enqueue(object : Callback<List<Book>> {
            override fun onResponse(call: Call<List<Book>>, response: Response<List<Book>>) {
                if (response.isSuccessful) {
                    bookList.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<Book>>, t: Throwable) {

            }

        })
    }

    fun hitDeletePost(id: Int) {
        val call: Call<Book> = postApi.deletePost(id)
        call.enqueue(object : Callback<Book> {
            override fun onResponse(call: Call<Book>, response: Response<Book>) {
                if (response.isSuccessful) {
                    Log.e(TAG, "onResponse: successfully delete")
                    isPostDeleted.value=true
                }
            }

            override fun onFailure(call: Call<Book>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}