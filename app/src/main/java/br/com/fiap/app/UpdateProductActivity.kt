package br.com.fiap.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import br.com.fiap.app.model.Product
import br.com.fiap.app.utils.RetrofitBuilder
import kotlinx.android.synthetic.main.activity_update_product.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UpdateProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_product)

        tvProductName.text = intent.getStringExtra("productName")
        etProductName.setText(intent.getStringExtra("productName"))
        tvProductId.text = intent.getStringExtra("productId")

        btSave.setOnClickListener{
            var product = Product()

            product.name = etProductName.text.toString()
            product.id =  tvProductId.text.toString().toInt()

            saveProduct(product)

            val data = Intent()
            setResult(RESULT_OK, data)
            finish()
        }
    }

    private fun saveProduct(product: Product) {
        val call = RetrofitBuilder().productService().updateProduct(id = product.id!!, product = product )
        call.enqueue(object : Callback<Product> {
            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                if (response.isSuccessful) {
                    Toast.makeText(
                        this@UpdateProductActivity,
                        getString(R.string.product_created),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<Product>, t: Throwable) {
                Log.e("ERROR: ", t.message)
            }
        })
    }
}
