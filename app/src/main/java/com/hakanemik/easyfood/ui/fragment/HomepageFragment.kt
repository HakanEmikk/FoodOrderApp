package com.hakanemik.easyfood.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.hakanemik.easyfood.R
import com.hakanemik.easyfood.databinding.FragmentHomepageBinding
import com.hakanemik.easyfood.ui.adapter.FoodsAdapter
import com.hakanemik.easyfood.ui.viewmodel.HomepageViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomepageFragment : Fragment() {
    private lateinit var binding: FragmentHomepageBinding
    private lateinit var viewModel: HomepageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentHomepageBinding.inflate(inflater,container,false)

        viewModel.foodsList.observe(viewLifecycleOwner){
            val foodsAdapter= FoodsAdapter(requireContext(),it,viewModel)
            binding.foodRv.adapter=foodsAdapter
        }
        binding.foodRv.layoutManager=GridLayoutManager(requireContext(),2)



        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: HomepageViewModel by viewModels()
        viewModel=tempViewModel
    }

    override fun onResume() {
        super.onResume()
        viewModel.allFoodsLoading()
    }

}