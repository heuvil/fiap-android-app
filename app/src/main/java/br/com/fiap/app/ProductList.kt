package br.com.fiap.app

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.fiap.app.adapter.ProductAdapter
import br.com.fiap.app.model.Product
import br.com.fiap.app.utils.RetrofitBuilder
import kotlinx.android.synthetic.main.activity_product_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductList : AppCompatActivity() {

    private var productList: ArrayList<Product> = ArrayList()
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        RetrofitBuilder()

        loadAllProducts()

    }


    private fun loadAllProducts(){
        val call = RetrofitBuilder().productService().getAllProductList()
        call.enqueue(object: Callback<ArrayList<Product>?> {
            override fun onResponse(call: Call<ArrayList<Product>?>,
                                    response: Response<ArrayList<Product>?>)
            {
                response?.body()?.let{
                    productList = it
                    fillProducts()
                }
            }

            override fun onFailure(call: Call<ArrayList<Product>?>,
                                   t: Throwable?){
                Toast.makeText(applicationContext, "Failed to load Product List", Toast.LENGTH_SHORT).show()
                Log.e("onFailure error", t?.message)
            }

        })
    }

    private fun fillProducts(){
        linearLayoutManager = LinearLayoutManager(this)
        recyclerViewProducts.layoutManager = linearLayoutManager
        adapter = ProductAdapter(productList)
        recyclerViewProducts.adapter = adapter
    }

}
