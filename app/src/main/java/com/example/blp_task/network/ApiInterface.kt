package com.example.blp_task.network

import com.example.blp_task.dataclass.ProductsItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("medList.php")
    fun getProducts():Call<List<ProductsItem>>
}