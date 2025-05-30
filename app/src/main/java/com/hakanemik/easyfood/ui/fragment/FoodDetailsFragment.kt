package com.hakanemik.easyfood.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
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
    ): View {
        binding = FragmentFoodDetailsBinding.inflate(inflater, container, false)
        val bundle: FoodDetailsFragmentArgs by navArgs()
        binding.toolbar.setNavigationIcon(R.drawable.close)
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        val incomingFood = bundle.food
        val foodName = incomingFood.yemek_adi

        val currentQuantity = viewModel.getQuantity(foodName)

        binding.tvQuantity.text = currentQuantity.toString()
        binding.tvFoodName.text = foodName
        binding.tvPrice.text = "₺ ${incomingFood.yemek_fiyat}"
        binding.btnAddToCart.text = "Sepete Ekle - ₺ ${incomingFood.yemek_fiyat * (currentQuantity.coerceAtLeast(1))}"
        binding.btnAddToCart.setOnClickListener {
            viewModel.addToCart(
                foodName,
                incomingFood.yemek_resim_adi,
                incomingFood.yemek_fiyat,
                "HakanEmik")
        }

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${incomingFood.yemek_resim_adi}"
        Glide.with(this).load(url).override(600, 600).into(binding.ivFoodImage)

        binding.btnIncrease.setOnClickListener {
            viewModel.increaseQuantity(foodName)
            val updatedQuantity = viewModel.getQuantity(foodName)
            binding.tvQuantity.text = updatedQuantity.toString()
            binding.btnAddToCart.text = "Sepete Ekle - ₺ ${incomingFood.yemek_fiyat * updatedQuantity}"

        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: FoodDetailsViewModel by viewModels()
        viewModel=tempViewModel
    }

}