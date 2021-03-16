package com.harish.customuinavbar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.harish.customuinavbar.adapters.ProductsAdapter
import com.harish.customuinavbar.adapters.ProductsListListener
import com.harish.customuinavbar.models.Chair
import com.harish.customuinavbar.viewmodels.HomeViewmodel
import com.harish.customuinavbar.viewmodels.NavBarSelection
import com.harish.customuinavbar.viewmodels.PageState
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_nav_bar.*

class MainActivity : AppCompatActivity() , ProductsListListener{

    private lateinit var viewModel:HomeViewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(HomeViewmodel::class.java)
        viewModel.showLoading()
        setupObservers()
        nav_home.setOnClickListener {
            viewModel.navSelction.value = NavBarSelection.HOME
        }
        nav_cart.setOnClickListener {
            viewModel.navSelction.value = NavBarSelection.CART
            Toast.makeText(this, "Cart clicked", Toast.LENGTH_SHORT).show()
        }
        nav_rated.setOnClickListener {
            viewModel.navSelction.value = NavBarSelection.RATED
            Toast.makeText(this, "Rated clicked", Toast.LENGTH_SHORT).show()

        }
        nav_account.setOnClickListener {
            viewModel.navSelction.value = NavBarSelection.ACCOUNT
            Toast.makeText(this, "Account clicked", Toast.LENGTH_SHORT).show()

        }
    }

    private fun setupObservers() {
        viewModel.apply {
            productsList.observe(this@MainActivity, Observer {
                loading_layout.visibility=View.GONE
                main_layout.visibility=View.VISIBLE
                val adapter=ProductsAdapter(it,this@MainActivity)
                list_products.adapter=adapter
                pageState.postValue(PageState.SUCCESS)
            })

            navSelction.observe(this@MainActivity, Observer {
                when(it){
                    NavBarSelection.HOME->{
                        frame_bag_selected.visibility = View.INVISIBLE
                        frame_home_selected.visibility = View.VISIBLE
                        frame_account_selected.visibility = View.INVISIBLE
                        frame_star_selected.visibility = View.INVISIBLE
                        nav_home.setImageResource(0)
                        nav_cart.setImageResource(R.drawable.ic_baseline_shopping_bag_24)
                        nav_rated.setImageResource(R.drawable.ic_baseline_star_rate_24)
                        nav_account.setImageResource(R.drawable.ic_baseline_person_24)
                        //load home Fragment
                    }
                    NavBarSelection.CART->{
                        frame_bag_selected.visibility = View.VISIBLE
                        frame_home_selected.visibility = View.INVISIBLE
                        frame_account_selected.visibility = View.INVISIBLE
                        frame_star_selected.visibility = View.INVISIBLE
                        nav_cart.setImageResource(0)
                        nav_rated.setImageResource(R.drawable.ic_baseline_star_rate_24)
                        nav_account.setImageResource(R.drawable.ic_baseline_person_24)
                        nav_home.setImageResource(R.drawable.ic_baseline_home_24)
                        //load cart fragment

                    }
                    NavBarSelection.RATED->{
                        frame_bag_selected.visibility = View.INVISIBLE
                        frame_home_selected.visibility = View.INVISIBLE
                        frame_account_selected.visibility = View.INVISIBLE
                        frame_star_selected.visibility = View.VISIBLE
                        nav_rated.setImageResource(0)
                        nav_account.setImageResource(R.drawable.ic_baseline_person_24)
                        nav_home.setImageResource(R.drawable.ic_baseline_home_24)
                        nav_cart.setImageResource(R.drawable.ic_baseline_shopping_bag_24)
                        //load rated fragment

                    }
                    NavBarSelection.ACCOUNT->{
                        frame_bag_selected.visibility = View.INVISIBLE
                        frame_home_selected.visibility = View.INVISIBLE
                        frame_account_selected.visibility = View.VISIBLE
                        frame_star_selected.visibility = View.INVISIBLE
                        nav_account.setImageResource(0)
                        nav_home.setImageResource(R.drawable.ic_baseline_home_24)
                        nav_cart.setImageResource(R.drawable.ic_baseline_shopping_bag_24)
                        nav_rated.setImageResource(R.drawable.ic_baseline_star_rate_24)
                        //load person fragment

                    }
                }
            })
        }
    }

    override fun onProductCardClicked(product: Chair) {

        viewModel.productDetails.postValue(product)
        startActivity(Intent(this,ProductDetailsActivity::class.java).putExtra("product", Gson().toJson(product)))

    }

    override fun onBuyClicked() {
        Toast.makeText(this, "Added to cart !!!", Toast.LENGTH_SHORT).show()
    }
}