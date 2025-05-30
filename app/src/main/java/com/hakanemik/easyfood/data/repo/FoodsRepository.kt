package com.hakanemik.easyfood.data.repo

import com.hakanemik.easyfood.data.datasource.FoodsDataSource
import com.hakanemik.easyfood.data.entity.AllFoods
import com.hakanemik.easyfood.data.entity.Basket

class FoodsRepository(var foodsDataSource: FoodsDataSource) {
    suspend fun allFoodsLoading(): List<AllFoods> = foodsDataSource.allFoodsLoading()
    suspend fun add(yemek_adi : String,
                    yemek_resim_adi : String,
                    yemek_fiyat : Int,
                    yemek_siparis_adet : Int,
                    kullanici_adi : String) {
        foodsDataSource.add(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi)

    }
    suspend fun basketLoading(kullanici_adi: String): List<Basket> = foodsDataSource.basketLoading(kullanici_adi)

    suspend fun basketDelete(sepet_yemek_id: Int, kullanici_adi: String)
    {
        foodsDataSource.basketDelete(sepet_yemek_id,kullanici_adi)

    }
}