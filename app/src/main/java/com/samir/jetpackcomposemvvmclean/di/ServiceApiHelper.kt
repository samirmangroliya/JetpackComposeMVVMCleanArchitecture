package com.samir.jetpackcomposemvvmclean.di

import com.samir.jetpackcomposemvvmclean.data.service.CatService
import com.samir.jetpackcomposemvvmclean.data.service.cats.CatApiServiceHelper
import com.samir.jetpackcomposemvvmclean.data.service.cats.CatApiServiceHelperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ServiceApiHelper {
    @Provides
    fun provideCatApiServiceHelper(catService: CatService): CatApiServiceHelper {
        return CatApiServiceHelperImpl(catService)
    }
}