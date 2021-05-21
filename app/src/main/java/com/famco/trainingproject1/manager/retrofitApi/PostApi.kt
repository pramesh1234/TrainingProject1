package com.famco.trainingproject1.manager.retrofitApi

import com.famco.trainingproject1.model.Book
import retrofit2.Call
import retrofit2.http.*

interface PostApi {
    @GET("/posts")
    fun getPosts(): Call<List<Book>>

    @POST("/posts")
    fun setPost(@Body book: Book, @Header("Content-type") contentType: String): Call<Book>

    @GET("/posts/{id}")
    fun getPost(@Path("id") id: Int): Call<Book>

    @PUT("/posts/{id}")
    fun getUpdatePost(
        @Path("id") id: Int,
        @Body book: Book,
        @Header("Content-type") contentType: String
    ): Call<Book>

    @DELETE("/posts/{id}")
    fun deletePost( @Path("id") id: Int) : Call<Book>
}