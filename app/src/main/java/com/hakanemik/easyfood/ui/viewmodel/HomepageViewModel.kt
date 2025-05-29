package com.hakanemik.easyfood.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hakanemik.easyfood.data.entity.AllFoods
import com.hakanemik.easyfood.data.repo.FoodsRepository
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class HomepageViewModel @Inject constructor(var foodsRepository: FoodsRepository) :ViewModel(){
    var foodsList = MutableLiveData<List<AllFoods>>()
    init {
        allFoodsLoading()
    }
    fun allFoodsLoading(){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                foodsList.value=foodsRepository.allFoodsLoading()
            }catch (e:Exception){
                Log.e("hata","yükleme hatası  $e")
            }
        }
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