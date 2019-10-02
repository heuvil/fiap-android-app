package br.com.fiap.app.service

import br.com.fiap.app.model.Product
import retrofit2.Call
import retrofit2.http.GET

interface ProductService {

    @GET("product")
    fun getAllProductList() : Call<ArrayList<Product>>

}