package com.samir.jetpackcomposemvvmclean.domain.repo

import com.samir.jetpackcomposemvvmclean.data.NetworkResult
import com.samir.jetpackcomposemvvmclean.data.models.SuccessResponse
import com.samir.jetpackcomposemvvmclean.data.models.catDetails.PostFavCatModel
import kotlinx.coroutines.flow.Flow

interface CatDetailsRepository {
    suspend fun postFavouriteCat(favCat: PostFavCatModel): Flow<NetworkResult<SuccessResponse>>
    suspend fun deleteFavouriteCat(
        imageId: String, favouriteId: Int
    ): Flow<NetworkResult<SuccessResponse>>
}