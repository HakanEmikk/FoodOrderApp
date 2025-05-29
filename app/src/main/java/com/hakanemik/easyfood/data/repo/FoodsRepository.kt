package com.hakanemik.easyfood.data.repo

import com.hakanemik.easyfood.data.datasource.FoodsDataSource
import com.hakanemik.easyfood.data.entity.AllFoods

class FoodsRepository(var foodsDataSource: FoodsDataSource) {
    suspend fun allFoodsLoading(): List<AllFoods> = foodsDataSource.allFoodsLoading()
    suspend fun add(yemek_adi : String,
                    yemek_resim_adi : String,
                    yemek_fiyat : Int,
                    yemek_siparis_adet : Int,
                    kullanici_adi : String) {
        foodsDataSource.add(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi)

    }
}