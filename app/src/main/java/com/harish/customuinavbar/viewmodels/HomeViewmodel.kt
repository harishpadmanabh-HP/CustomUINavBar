package com.harish.customuinavbar.viewmodels

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.harish.customuinavbar.models.Chair
import com.harish.customuinavbar.repository.ProductsRepository

class HomeViewmodel :ViewModel(){

    val repository = ProductsRepository()
    val productsList = MutableLiveData<List<Chair>>()
    val pageState = MutableLiveData<PageState>().also {
        it.postValue(PageState.LOADING)
    }
    val navSelction=MutableLiveData<NavBarSelection>().also {
        it.postValue(NavBarSelection.HOME)
    }

    val productDetails = MutableLiveData<Chair>()

    fun getProducts(){
        productsList.postValue(repository.getProducts())
    }

    fun showLoading(){
        Handler().postDelayed({
           pageState.postValue(PageState.SUCCESS)
            getProducts()
        }, 5000)
    }


}

enum class PageState{
    LOADING,SUCCESS
}

enum class NavBarSelection{
    HOME,CART,RATED,ACCOUNT
}