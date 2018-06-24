package com.keepbrain.app.server.responses

import com.google.gson.annotations.SerializedName

data class Response<T>(@SerializedName(value = "rcs") var rcs: String,
                       @SerializedName(value = "placements") var placements: T)