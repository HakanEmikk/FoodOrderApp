package com.hakanemik.easyfood.data.datasource

import android.util.Log
import com.hakanemik.easyfood.data.entity.AllFoods
import com.hakanemik.easyfood.data.entity.Basket
import com.hakanemik.easyfood.retrofit.FoodsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FoodsDataSource(var foodsDao: FoodsDao) {
    suspend fun allFoodsLoading() : List<AllFoods> = withContext(Dispatchers.IO){
        return@withContext foodsDao.allFoodsLoading().yemekler
    }
    suspend fun add(yemek_adi : String,
                    yemek_resim_adi : String,
                    yemek_fiyat : Int,
                    yemek_siparis_adet : Int,
                    kullanici_adi : String){
        val CRUDAnswer=foodsDao.add(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi)
        Log.e("dataSource",CRUDAnswer.success.toString())
    }
    suspend fun basketLoading(kullanici_adi: String) : List<Basket> = withContext(Dispatchers.IO){
        return@withContext foodsDao.basket(kullanici_adi).sepet_yemekler

    }
    suspend fun basketDelete(sepet_yemek_id: Int, kullanici_adi: String){
        val CRUDAnswer=foodsDao.delete(sepet_yemek_id,kullanici_adi)
        Log.e("dataSource",CRUDAnswer.success.toString())

    }
}