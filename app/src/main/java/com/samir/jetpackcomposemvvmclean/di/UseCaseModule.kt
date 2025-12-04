package com.samir.jetpackcomposemvvmclean.di

import android.view.View
import com.samir.jetpackcomposemvvmclean.domain.repo.CatsRepository
import com.samir.jetpackcomposemvvmclean.domain.usercase.cats.GetCatsUseCase
import com.samir.jetpackcomposemvvmclean.domain.usercase.cats.GetCatsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    @Provides
    fun provideCatsUseCase(catsRepository: CatsRepository): GetCatsUseCase {
        return GetCatsUseCaseImpl(catsRepository)
    }
}