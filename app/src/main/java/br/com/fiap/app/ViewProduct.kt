package br.com.fiap.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_product.*

class ViewProduct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_product)

        tvProductName.text = intent.getStringExtra("productName")
        tvProductId.text = intent.getStringExtra("productId")

    }
}
