package com.hakanemik.easyfood.retrofit

import com.hakanemik.easyfood.data.entity.AllFoodAnswer
import com.hakanemik.easyfood.data.entity.BasketAnswer
import com.hakanemik.easyfood.data.entity.CRUDAnswer
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface FoodsDao {
    @GET("yemekler/tumYemekleriGetir.php")
    suspend fun allFoodsLoading() : AllFoodAnswer

    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    suspend fun add(@Field("yemek_adi") yemek_adi:String,
                    @Field("yemek_resim_adi") yemek_resim_adi:String,
                    @Field("yemek_fiyat") yemek_fiyat:Int,
                    @Field("yemek_siparis_adet") yemek_siparis_adet:Int,
                    @Field("kullanici_adi") kullanici_adi:String) : CRUDAnswer
    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    suspend fun basket(@Field("kullanici_adi") kullanici_adi:String) : BasketAnswer

    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    suspend fun delete(@Field("sepet_yemek_id") sepet_yemek_id:Int,
                       @Field("kullanici_adi") kullanici_adi:String) : CRUDAnswer

}