package com.harish.customuinavbar.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.harish.customuinavbar.R
import com.harish.customuinavbar.models.Chair
import kotlinx.android.synthetic.main.product_row.view.*

interface ProductsListListener{
    fun onProductCardClicked(product: Chair)
    fun onBuyClicked()
}

class ProductsAdapter (val products:List<Chair>,val listener: ProductsListListener):
    RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.product_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val item = products[position]

        holder.itemView.apply {
            Glide.with(context).load(item.image).into(product_image)
            title_textview.text=item.name
            manufacturer_textview.text="by ${item.manufacturer}"
            desc_textview.text=item.desc
            price_textview.text = "$ ${item.price}"
            buy_button.setOnClickListener {
                listener.onBuyClicked()
            }
            setOnClickListener {
                listener.onProductCardClicked(item)
            }
        }

    }

}