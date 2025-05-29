package com.hakanemik.easyfood.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.hakanemik.easyfood.data.repo.FoodsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FoodDetailsViewModel @Inject constructor(var foodsRepository : FoodsRepository) : ViewModel() {

}