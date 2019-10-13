package br.com.fiap.app.service

import br.com.fiap.app.model.Product
import retrofit2.Call
import retrofit2.http.*


interface ProductService {

    @GET("product")
    fun getAllProductList() : Call<ArrayList<Product>>

    @GET("product/{id}")
    fun findProduct(@Body product: Product): Call<Product>

    @POST("product")
    fun addProduct(@Body product: Product): Call<Product>

    @PATCH("product/{id}")
    fun updateProduct(@Path("id") id: Int, @Body product: Product): Call<Product>

    @DELETE("product/{id}")
    fun deleteProduct(@Path("id") id: Int): Call<Void>

}