package com.hakanemik.easyfood.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.hakanemik.easyfood.R
import com.hakanemik.easyfood.databinding.FragmentFoodDetailsBinding
import com.hakanemik.easyfood.ui.viewmodel.FoodDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodDetailsFragment : Fragment() {
    private lateinit var binding: FragmentFoodDetailsBinding
    private lateinit var viewModel: FoodDetailsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentFoodDetailsBinding.inflate(inflater,container,false)
        val bundle : FoodDetailsFragmentArgs by navArgs()
        val incomingFood=bundle.food
        val incomingFoodOrderQuantity=bundle.foodOrderQuantity
        binding.tvFoodName.setText(incomingFood.yemek_adi)
        binding.tvPrice.setText("₺ ${incomingFood.yemek_fiyat.toString()}")
        binding.btnAddToCart.text="Sepete Ekle - ₺ ${incomingFood.yemek_fiyat.toString()}"
        binding.tvQuantity.text=incomingFoodOrderQuantity.toString()
        val url="http://kasimadalan.pe.hu/yemekler/resimler/${incomingFood.yemek_resim_adi}"
        Glide.with(this).load(url).override(600,600).into(binding.ivFoodImage)


        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: FoodDetailsViewModel by viewModels()
        viewModel=tempViewModel
    }

}