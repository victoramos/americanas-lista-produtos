package com.example.victor.testb2w.viewmodel

import android.text.Html
import com.example.victor.testb2w.model.Placements
import com.example.victor.testb2w.repository.ProductRepository
import com.example.victor.testb2w.view.adapter.Header
import com.example.victor.testb2w.view.adapter.Product
import com.example.victor.testb2w.view.adapter.ProductsAdapter
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class ProductViewModel{
    val loadingEvent = PublishSubject.create<Boolean>()!!
    private var repositoryManager = ProductRepository()

    internal fun getHomeProducts(): Observable<List<Placements>> {
        loadingEvent.onNext(true)

        return repositoryManager.getProducts("home_page.rr1|home_page.rr2|home_page.rr3|home_page.history|home_page.banners_dest_prod_1|home_page.banners_dest_prod_2|home_page.banners_dest_prod_3|home_page.banners_dest_prod_4|home_page.banners_dest_prod_5|home_page.banners_dest_prod_6|home_page.banners_dest_prod_7|home_page.banners_dest_prod_8|home_page.banners_dest_prod_9|home_page.banners_dest_prod_10")
                .map {
                    it.placements
                }
                .doFinally { loadingEvent.onNext(false) }
                .onErrorReturn {
                    emptyList()
                }
    }

    private fun formatPriceForLocale(value: Int): String {
        val format = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
        return format.format(value)
    }

    private fun formatHtmlString(htmlText: String): String {
        return Html.fromHtml(htmlText).toString()
    }

    private fun convertPriceValue(value: Int): Int {
        return value / 100
    }

    fun getSectionedList(placements: List<Placements>): ArrayList<ProductsAdapter.Item> {
        val ret = arrayListOf<ProductsAdapter.Item>()

        for (item in placements){
            val header = Header()
            header.name = item.strategyMessage
            ret.add(header)

            for(recommendedProduct in item.recommendedProducts){
                val product = Product()
                product.thumb = recommendedProduct.imageURL
                product.name = formatHtmlString(recommendedProduct.name)
                product.productUrl = recommendedProduct.productURL
                product.price = formatPriceForLocale(convertPriceValue(recommendedProduct.price))

                ret.add(product)
            }
        }

        return ret
    }
}