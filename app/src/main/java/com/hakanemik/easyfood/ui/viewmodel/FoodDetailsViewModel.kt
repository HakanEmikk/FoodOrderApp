package com.hakanemik.easyfood.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hakanemik.easyfood.data.repo.FoodsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodDetailsViewModel @Inject constructor(var foodsRepository : FoodsRepository) : ViewModel() {


    private val _orderQuantities = mutableMapOf<String, Int>()


    val orderQuantities: Map<String, Int> get() = _orderQuantities

    fun getQuantity(foodName: String): Int {
        return _orderQuantities[foodName] ?: 0
    }

    fun increaseQuantity(foodName: String) {
        val newQuantity = getQuantity(foodName) + 1
        _orderQuantities[foodName] = newQuantity
    }

    fun addToCart(foodName: String, foodImage: String, foodPrice: Int, username: String) {
        val quantity = getQuantity(foodName)
        add(foodName, foodImage, foodPrice, quantity, username)
    }
    fun add(yemek_adi : String,
            yemek_resim_adi : String,
            yemek_fiyat : Int,
            yemek_siparis_adet : Int,
            kullanici_adi : String){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                foodsRepository.add(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi)
            }catch (e:Exception){
                Log.e("hata","ekleme hatası $e")
            }
        }
}
}