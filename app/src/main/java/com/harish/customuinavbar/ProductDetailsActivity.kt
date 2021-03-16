package com.harish.customuinavbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.harish.customuinavbar.models.Chair
import com.harish.customuinavbar.viewmodels.HomeViewmodel
import kotlinx.android.synthetic.main.activity_product_details.*
import kotlinx.android.synthetic.main.detail_layout.*

class ProductDetailsActivity : AppCompatActivity() {
    private lateinit var viewModel: HomeViewmodel
    private lateinit var product:Chair

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)
        viewModel = ViewModelProvider(this).get(HomeViewmodel::class.java)
        product = Gson().fromJson(intent.getStringExtra("product"),Chair::class.java)

        rendeData(product)
    }

    private fun rendeData(product: Chair?) {

        if (product != null) {
            Glide.with(this).load(product.image).into(product_image)
            title_tv.text = product.name
            maker_tv.text= "By ${product.manufacturer}"
            desc_tv.text = product.longDesc
            price_tv.text= "$ ${product.price}"
            rating_tv.text=product.rating.toString()

        }

    }

    fun onBuyClicked(view: View) {}


}