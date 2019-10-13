package br.com.fiap.app.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.app.R
import br.com.fiap.app.UpdateProductActivity
import br.com.fiap.app.ViewProduct
import br.com.fiap.app.model.Product
import br.com.fiap.app.utils.RetrofitBuilder
import kotlinx.android.synthetic.main.activity_product.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductAdapter(private var productList: MutableList<Product>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(){

    val UPDATE_PRODUCT = 1

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) : ProductViewHolder {
        val context = viewGroup.context
        val inflatter = LayoutInflater.from(context)
        val shouldAttachToParentImmediately = false

        val view = inflatter.inflate(R.layout.activity_product, viewGroup, shouldAttachToParentImmediately)

        return ProductViewHolder(view)


    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = productList[position]
        holder.bindProduct(item)


        holder.itemView.btDeleteProduct.setOnClickListener{
            deleteProduct(holder)
        }

        holder.itemView.btUpdateProduct.setOnClickListener{
            Intent(holder.itemView.context, UpdateProductActivity::class.java).also { intent ->
                var pHolder = holder as ProductViewHolder
                var id = pHolder.tvId.text
                var name = pHolder.name.text
                intent.putExtra("productId",id)
                intent.putExtra("productName",name)
                (holder.itemView.context as Activity).startActivityForResult(intent, UPDATE_PRODUCT)
                notifyDataSetChanged()
             }
        }
    }

    fun deleteProduct(holder: ProductViewHolder) {

        val call = RetrofitBuilder().productService().deleteProduct(id = holder.itemView.tvProductId.text.toString().toInt() )
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Toast.makeText(
                        holder.itemView.context,
                        holder.itemView.context.getString(R.string.product_deleted),
                        Toast.LENGTH_SHORT
                    ).show()

                    productList.removeAt(holder.adapterPosition)
                    notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e("ERROR: ", t.message)
            }
        })
    }




    class ProductViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        private var view: View = v
        private lateinit var product : Product
        private var id: Int? = 0
        var name : TextView
        var tvId : TextView

        init{

            name = view.findViewById(R.id.tvProductName)
            tvId = view.findViewById(R.id.tvProductId)

            v.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val context = itemView.context
            val intent = Intent(context, ViewProduct::class.java)
            intent.putExtra("productId", tvId.text.toString())
            intent.putExtra("productName", name.text.toString())
            context.startActivity(intent)
        }

        fun bindProduct(product: Product){
            this.product = product
            name.text = product.name
            tvId.text = product.id.toString()
            id = product.id
        }

    }
}