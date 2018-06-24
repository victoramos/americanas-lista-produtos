package com.example.victor.testb2w.model

import com.google.gson.annotations.SerializedName

data class Placements(@SerializedName("strategyMessage") var strategyMessage: String,
                      @SerializedName("recommendedProducts") var recommendedProducts: List<RecommendedProducts>
)