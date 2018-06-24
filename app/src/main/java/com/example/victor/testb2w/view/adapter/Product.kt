package com.example.victor.testb2w.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.victor.testb2w.R

class Product: ProductsAdapter.Item{
    var thumb = ""
    var name = ""
    var price = ""
    var productUrl = ""

    override val viewType: ProductsAdapter.Item.RowType
        get() = ProductsAdapter.Item.RowType.LIST_ITEM

    override fun getViewHolder(inflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.item_product, parent, false))
    }

    internal inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView){
        var thumb: ImageView? = itemView?.findViewById(R.id.image_product)
        var name: TextView? = itemView?.findViewById(R.id.text_name)
        var price: TextView? = itemView?.findViewById(R.id.text_price)
    }
}