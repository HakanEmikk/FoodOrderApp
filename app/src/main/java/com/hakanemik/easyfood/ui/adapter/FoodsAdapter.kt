package com.hakanemik.easyfood.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hakanemik.easyfood.data.entity.AllFoods
import com.hakanemik.easyfood.databinding.CardDesignBinding
import com.hakanemik.easyfood.ui.fragment.HomepageFragmentDirections
import com.hakanemik.easyfood.ui.viewmodel.HomepageViewModel
import com.hakanemik.easyfood.utils.connect

class FoodsAdapter(var mContext: Context,
                   var foodsList:List<AllFoods>,
                   var viewModel: HomepageViewModel)
    : RecyclerView.Adapter<FoodsAdapter.CardTasarimTutucu>() {
        private val orderQuantities = mutableMapOf<String, Int>()


    inner class CardTasarimTutucu(var tasarim : CardDesignBinding) : RecyclerView.ViewHolder(tasarim.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val tasarim= CardDesignBinding.inflate(LayoutInflater.from(mContext),parent,false)
        return CardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val food=foodsList.get(position)
        val t=holder.tasarim

        val foodOrderQuantity = orderQuantities[food.yemek_adi] ?: 0

        t.tvFoodName.text=food.yemek_adi
        t.tvFoodPrice.text="₺ ${food.yemek_fiyat.toString()}"
        val url="http://kasimadalan.pe.hu/yemekler/resimler/${food.yemek_resim_adi}"
        Glide.with(mContext).load(url).override(400,400).into(t.ivFoodImage)

        t.cardViewDesign.setOnClickListener {
            val connect=HomepageFragmentDirections.detailsConnect(food=food,foodOrderQuantity)
            Navigation.connect(it,connect)
        }
        t.btnAddToCart.setOnClickListener {
            val newQuantity = (orderQuantities[food.yemek_adi] ?: 0) + 1
            orderQuantities[food.yemek_adi] = newQuantity
            viewModel.add(food.yemek_adi, food.yemek_resim_adi, food.yemek_fiyat, newQuantity, "HakanEmik")

        }
    }

    override fun getItemCount(): Int {
        return foodsList.size
    }

}