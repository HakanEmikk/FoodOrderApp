package com.hakanemik.easyfood.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hakanemik.easyfood.data.entity.Basket
import com.hakanemik.easyfood.data.repo.FoodsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel  @Inject constructor(var foodsRepository : FoodsRepository) : ViewModel() {
   var basketList = MutableLiveData<List<Basket>>()
    init {
        basketLoading("HakanEmik")
    }
    fun basketLoading(kullanici_adi:String){
        CoroutineScope ( Dispatchers.Main).launch {
            try {
                basketList.value=foodsRepository.basketLoading(kullanici_adi)
            }catch (e:Exception){
                Log.e("hata","yükleme hatası  $e")
            }
        }
    }
    fun basketDelete(sepet_yemek_id: Int, kullanici_adi: String){
        CoroutineScope ( Dispatchers.Main).launch {
            try {
                foodsRepository.basketDelete(sepet_yemek_id,kullanici_adi)
                basketLoading("HakanEmik")
            }catch (e:Exception){
                Log.e("hata","silme hatası  $e")
            }
        }
    }


}