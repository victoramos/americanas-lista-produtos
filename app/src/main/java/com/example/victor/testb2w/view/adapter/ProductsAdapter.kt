package com.example.victor.testb2w.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.victor.testb2w.model.Placements
import io.reactivex.subjects.PublishSubject
import java.text.NumberFormat
import java.util.*


class ProductsAdapter(private var mContext: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var data: MutableList<Item> = arrayListOf()
    val clickEvent = PublishSubject.create<String>()!!

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when(Item.RowType.values()[viewType]) {
            Item.RowType.HEADER_ITEM -> {
                Header().getViewHolder(inflater, parent)
            }

            Item.RowType.LIST_ITEM -> {
                Product().getViewHolder(inflater, parent)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = data[position]

        when(item.viewType){
            Item.RowType.HEADER_ITEM ->{
                val headerHolder = holder as Header.ViewHolder
                val currentItem = item as Header

                headerHolder.name?.text = currentItem.name
            }
            else -> {
                val productHolder = holder as Product.ViewHolder
                val currentItem = item as Product

                productHolder.name?.text = currentItem.name
                productHolder.price?.text = currentItem.price

                if(mContext != null){
                    Glide.with(mContext!!).load(currentItem.thumb).into(productHolder.thumb!!)
                }

                productHolder.itemView.setOnClickListener({
                    clickEvent.onNext(currentItem.productUrl)
                })
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return data[position].viewType.ordinal
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setData(data: List<Placements>){
        for (item in data){
            val header = Header()
            header.name = item.strategyMessage
            this.data.add(header)

            for(recommendedProduct in item.recommendedProducts){
                val product = Product()
                product.thumb = recommendedProduct.imageURL
                product.name = Html.fromHtml(recommendedProduct.name).toString()
                product.productUrl = recommendedProduct.productURL

                val format = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
                product.price = format.format((recommendedProduct.price/100))

                this.data.add(product)
            }
        }

        notifyDataSetChanged()
    }

    interface Item {
        enum class RowType {
            LIST_ITEM, HEADER_ITEM
        }

        val viewType: RowType
        fun getViewHolder(inflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder
    }
}