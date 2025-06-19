package com.verifone.core.network.mapper

import com.verifone.core.domain.model.Product
import com.verifone.core.domain.model.Product.Rating
import com.verifone.core.network.dto.ProductDto

fun ProductDto.toDomain(): Product {
    return Product(
        id = id,
        title = title,
        price = price,
        description = description,
        category = category,
        image = image,
        rating = Rating(rate = rating.rate, count = rating.count)
    )
}
