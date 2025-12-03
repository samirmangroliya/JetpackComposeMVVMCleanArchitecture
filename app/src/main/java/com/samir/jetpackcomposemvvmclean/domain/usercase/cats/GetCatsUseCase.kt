package com.samir.jetpackcomposemvvmclean.domain.usercase.cats

import com.samir.jetpackcomposemvvmclean.data.NetworkResult
import com.samir.jetpackcomposemvvmclean.data.models.CatDataModel
import kotlinx.coroutines.flow.Flow

interface GetCatsUseCase {
    suspend fun getCats(): Flow<NetworkResult<List<CatDataModel>>>
}