package com.samir.jetpackcomposemvvmclean.di

import com.samir.jetpackcomposemvvmclean.data.repo.CatsRepoImpl
import com.samir.jetpackcomposemvvmclean.data.service.cats.CatApiServiceHelper
import com.samir.jetpackcomposemvvmclean.domain.repo.CatsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object RepoModules{

    @Provides
    fun provideCatRepo(catApiServiceHelper: CatApiServiceHelper): CatsRepository {
        return CatsRepoImpl(catApiServiceHelper)
    }

}