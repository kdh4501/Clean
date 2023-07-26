package com.dhkim.clean.module

import com.dhkim.clean.viewmodel.MainViewModel
import com.dhkim.clean.viewmodel.RoomViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MainViewModel()
    }

    viewModel {
        RoomViewModel(get())
    }
}