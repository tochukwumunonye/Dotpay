package com.example.dotpay.data.remote.makeupdto

import com.example.dotpay.domain.model.Makeup

data class MakeupDtoItem(
    val api_featured_image: String,
    val brand: String,
    val description: String,
    val image_link: String,
    val name: String,
    val price: String,
)

fun MakeupDtoItem.toMakeup(): Makeup {
    return Makeup(
        api_featured_image = api_featured_image,
        brand = brand,
        description =  description,
        image_link = image_link,
        name = name,
        price = price
    )
}
