package com.orange.test.util

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel


interface ViewModelBinder {
    fun bind(viewDataBinding: ViewDataBinding?, viewModel: ViewModel?)
}