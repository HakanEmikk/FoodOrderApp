package com.hakanemik.easyfood.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hakanemik.easyfood.R
import com.hakanemik.easyfood.databinding.FragmentBasketBinding
import com.hakanemik.easyfood.ui.adapter.BasketAdapter
import com.hakanemik.easyfood.ui.viewmodel.BasketViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel

@AndroidEntryPoint
class BasketFragment : Fragment() {
    private lateinit var binding:FragmentBasketBinding
    private lateinit var viewModel: BasketViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentBasketBinding.inflate(inflater,container,false)
        binding.toolbar2.setNavigationIcon(R.drawable.close)
        binding.toolbar2.setNavigationOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        binding.basketRv.layoutManager = LinearLayoutManager(requireContext())

        // Observer içinde hem adapter hem de toplam hesaplama
        viewModel.basketList.observe(viewLifecycleOwner) { basketItems ->
            // Adapter'ı güncelle
            val adapter = BasketAdapter(requireContext(), basketItems, viewModel)
            binding.basketRv.adapter = adapter

            // Toplamı hesapla
            val total = basketItems?.sumOf {
                it.yemek_fiyat * it.yemek_siparis_adet
            } ?: 0

            binding.tvToplam.text = "₺$total"
        }


        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: BasketViewModel by viewModels()
        viewModel=tempViewModel
    }

}