package com.hakanemik.easyfood.data.entity

import java.io.Serializable


data class AllFoods(var yemek_id:Int,
                    var yemek_adi:String,
                    var yemek_resim_adi:String,
                    var yemek_fiyat:Int) : Serializable {

}
