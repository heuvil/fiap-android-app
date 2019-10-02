package br.com.fiap.app.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.app.R
import br.com.fiap.app.model.Product

class ProductAdapter(private var productList: MutableList<Product>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(){
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) : ProductAdapter.ProductViewHolder {
        val context = viewGroup.context
        val inflatter = LayoutInflater.from(context)
        val shouldAttachToParentImmediately = false

        val view = inflatter.inflate(R.layout.activity_product_list, viewGroup, shouldAttachToParentImmediately)

        return ProductViewHolder(view)


    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductAdapter.ProductViewHolder, position: Int) {
        val item = productList[position]
        holder.bindProduct(item)
    }


    class ProductViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        private var view: View
        private lateinit var product : Product
        private var id: Int? = 0
        private var name : TextView

        override fun onClick(v: View?) {
            val context = itemView.context
            val intent = Intent(context, Product::class.java)
            intent.putExtra("Id", id)
            intent.putExtra("productName", name.text.toString())
            context.startActivity(intent)
        }

        init{
            view = v

            name = view.findViewById(R.id.tvProductName)

            v.setOnClickListener(this)
        }

        fun bindProduct(product : Product){
            this.product = product
            name.text = product.name
            id = product.id
        }

    }

}