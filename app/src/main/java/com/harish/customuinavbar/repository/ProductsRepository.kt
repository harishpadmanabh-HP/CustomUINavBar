package com.harish.customuinavbar.repository

import com.harish.customuinavbar.R
import com.harish.customuinavbar.models.Chair

class ProductsRepository{

    val product1=Chair(
        "Irul Chairs",
        R.drawable.sofa_1,
        " SS Furniture ",
        "120",
        "Engineered for Comfort",
        "SS Furniture And Jio Plywood - Offering Suede White Single Seat Sofa at Rs 6000/piece in Meerut, Uttar Pradesh. Read about company.",
        9.0f


    )
    val product2=Chair(
        "Mask Chairs",
        R.drawable.sofa_2,
        " SS Furniture ",
        "129",
        "Engineered for Comfort",
        "SS Furniture And Jio Plywood - Offering Suede White Single Seat Sofa at Rs 6000/piece in Meerut, Uttar Pradesh. Read about company.",
        9.0f


    )
    val product3=Chair(
        "Bean Chairs",
        R.drawable.sofa_3,
        " SS Furniture ",
        "130",
        "Engineered for Comfort",
        "SS Furniture And Jio Plywood - Offering Suede White Single Seat Sofa at Rs 6000/piece in Meerut, Uttar Pradesh. Read about company.",
        9.0f


    )
    val product4=Chair(
        "Comfy Chairs",
        R.drawable.sofa_1,
        " SS Furniture ",
        "120",
        "Engineered for Comfort and enjoying your sitting",
        "SS Furniture And Jio Plywood - Offering Suede White Single Seat Sofa at Rs 6000/piece in Meerut, Uttar Pradesh. Read about company.",
        9.0f


    )

    fun getProducts()= listOf<Chair>(product1,product2,product3,product4)


}