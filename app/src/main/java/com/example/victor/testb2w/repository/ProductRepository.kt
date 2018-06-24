package com.example.victor.testb2w.repository

import com.example.victor.testb2w.model.Placements
import com.keepbrain.app.server.ServerAPI
import com.keepbrain.app.server.responses.Response
import io.reactivex.Observable

class ProductRepository{
    private var productAPI = ServerAPI.instance.containers()

    fun getProducts(placements: String): Observable<Response<List<Placements>>> {
        return productAPI.getAllProducts(placements)
    }
}