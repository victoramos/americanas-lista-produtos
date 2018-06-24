package com.example.victor.testb2w.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.victor.testb2w.R

class Header: ProductsAdapter.Item{
    var name: String = ""

    override val viewType: ProductsAdapter.Item.RowType
        get() = ProductsAdapter.Item.RowType.HEADER_ITEM

    override fun getViewHolder(inflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.item_header, parent, false))
    }

    internal inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView){
        var name: TextView? = itemView?.findViewById(R.id.text_name)
    }
}