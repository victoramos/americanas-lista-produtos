package com.example.victor.americanas.server.requests

import com.example.victor.testb2w.model.Placements
import com.keepbrain.app.server.responses.Response
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductRequests {
    @GET("recsForPlacements")
    fun getAllProducts(@Query("placements") placements: String): Observable<Response<List<Placements>>>
}