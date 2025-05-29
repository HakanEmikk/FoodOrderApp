package com.hakanemik.easyfood.utils

import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.Navigation

fun Navigation.connect(it: View, id: Int) {
    findNavController(it).navigate(id)
}
fun Navigation.connect(it: View, id: NavDirections)  {
    findNavController(it).navigate(id)
}