package com.dicoding.capstone.mypixel.di

import com.dicoding.capstone.core.domain.usecase.PixelInteractor
import com.dicoding.capstone.core.domain.usecase.PixelUseCase
import com.dicoding.capstone.mypixel.ui.detail.DetailPixelViewModel
import com.dicoding.capstone.mypixel.ui.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<PixelUseCase> { PixelInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailPixelViewModel(get()) }
}