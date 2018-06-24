package com.example.victor.testb2w.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.victor.testb2w.R
import com.example.victor.testb2w.view.adapter.ProductsAdapter
import com.example.victor.testb2w.view.adapter.SectionedGridLayoutManager
import com.example.victor.testb2w.view.dialog.RequestErrorDialog
import com.example.victor.testb2w.viewmodel.ProductViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var mAdapter: ProductsAdapter? = null

    private val mViewModel: ProductViewModel by lazy {
        ProductViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAdapter = ProductsAdapter(this)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_products)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = SectionedGridLayoutManager(mAdapter!!, this, 2)
        recyclerView.adapter = mAdapter

        mViewModel.getHomeProducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    mAdapter!!.setData(mViewModel.getSectionedList(it))
                }, {
                    RequestErrorDialog().getDialog(this).show()
                })

        mAdapter!!.clickEvent.subscribe({
            val intent = Intent(this, ProductDetailActivity::class.java)
            intent.putExtra("productUrl", it)
            startActivity(intent)
        })

        mViewModel.loadingEvent
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if(it){
                        progress_loading.visibility = View.VISIBLE
                    }else{
                        progress_loading.visibility = View.GONE
                    }
                })
    }
}
