package com.example.victor.testb2w.model

import com.google.gson.annotations.SerializedName

data class RecommendedProducts(@SerializedName("imageURL") var imageURL: String,
                               @SerializedName("name") var name: String,
                               @SerializedName("priceCents") var price: Int,
                               @SerializedName("productURL") var productURL: String
)