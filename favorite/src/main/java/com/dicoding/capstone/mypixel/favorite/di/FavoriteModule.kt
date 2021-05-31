package com.dicoding.capstone.mypixel.favorite.di

import com.dicoding.capstone.mypixel.favorite.FavoriteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoriteViewModel(get()) }
}