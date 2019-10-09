package br.com.fiap.app.utils

import br.com.fiap.app.service.ProductService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitBuilder {

    //Timeout de 30s para api da Heroku subir quando estiver hibernando
    private val okHttp = OkHttpClient.Builder()
        .callTimeout(30, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api-product-crud.herokuapp.com/")
        .client(okHttp)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun productService(): ProductService = retrofit.create(ProductService::class.java)

}