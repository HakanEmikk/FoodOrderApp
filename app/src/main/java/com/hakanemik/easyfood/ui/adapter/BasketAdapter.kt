package com.hakanemik.easyfood.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hakanemik.easyfood.data.entity.AllFoods
import com.hakanemik.easyfood.data.entity.Basket
import com.hakanemik.easyfood.databinding.BasketCardDesignBinding
import com.hakanemik.easyfood.ui.viewmodel.BasketViewModel


class BasketAdapter(var mContext: Context,
                    var BasketList:List<Basket>,
                    var viewModel: BasketViewModel):RecyclerView.Adapter<BasketAdapter.BasketCardTasarimTutucu>()
{
    inner class BasketCardTasarimTutucu(var tasarim: BasketCardDesignBinding) : RecyclerView.ViewHolder(tasarim.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketCardTasarimTutucu {
        val tasarim=BasketCardDesignBinding.inflate(LayoutInflater.from(mContext),parent,false)
        return BasketCardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: BasketCardTasarimTutucu, position: Int) {
        val basket=BasketList.get(position)
        val t=holder.tasarim
        t.tvFoodName.text=basket.yemek_adi
        t.tvPrice.text="Fiyat: ₺${basket.yemek_fiyat.toString()}"
        t.tvQuantity.text="Adet: ${basket.yemek_siparis_adet.toString()}"
        val url="http://kasimadalan.pe.hu/yemekler/resimler/${basket.yemek_resim_adi}"
        Glide.with(mContext).load(url).override(400,400).into(t.ivFoodImage)
        t.tvTotalPrice.text="₺${basket.yemek_fiyat*basket.yemek_siparis_adet}"
        t.ivDelete.setOnClickListener {
            viewModel.basketDelete(basket.sepet_yemek_id,"HakanEmik")

            viewModel.basketLoading("HakanEmik")
        }
    }

    override fun getItemCount(): Int {
          return  BasketList.size
    }
}