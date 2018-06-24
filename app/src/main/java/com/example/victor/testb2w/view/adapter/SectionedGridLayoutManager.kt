package com.example.victor.testb2w.view.adapter

import android.content.Context
import android.support.v7.widget.GridLayoutManager

class SectionedGridLayoutManager(productsAdapter: ProductsAdapter, context: Context, spanCount: Int) : GridLayoutManager(context, spanCount) {
    private fun commonInit(productsAdapter: ProductsAdapter){
        spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when(ProductsAdapter.Item.RowType.values()[productsAdapter.getItemViewType(position)]  ) {
                    ProductsAdapter.Item.RowType.LIST_ITEM -> 1
                    else -> spanCount
                }
            }
        }
    }

    init {
        commonInit(productsAdapter)
    }
}