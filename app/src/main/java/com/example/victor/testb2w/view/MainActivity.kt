package com.example.victor.testb2w.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.victor.testb2w.R
import com.example.victor.testb2w.view.adapter.ProductsAdapter
import com.example.victor.testb2w.view.adapter.SectionedGridLayoutManager
import com.keepbrain.app.server.ServerAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MainActivity : AppCompatActivity() {
    private var mAdapter: ProductsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAdapter = ProductsAdapter(this)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_products)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = SectionedGridLayoutManager(mAdapter!!, this, 2)
        recyclerView.adapter = mAdapter

        ServerAPI.instance.containers().getAllProducts("home_page.rr1|home_page.rr2|home_page.rr3|home_page.history|home_page.banners_dest_prod_1|home_page.banners_dest_prod_2|home_page.banners_dest_prod_3|home_page.banners_dest_prod_4|home_page.banners_dest_prod_5|home_page.banners_dest_prod_6|home_page.banners_dest_prod_7|home_page.banners_dest_prod_8|home_page.banners_dest_prod_9|home_page.banners_dest_prod_10")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map {
                    it.placements
                }
                .subscribe({
                    mAdapter!!.setData(it)
                }, {
                    Log.i("Dima", "erro")
                })

        mAdapter!!.clickEvent.subscribe({
            val intent = Intent(this, ProductDetail::class.java)
            intent.putExtra("productUrl", it)
            startActivity(intent)
        })
    }
}
