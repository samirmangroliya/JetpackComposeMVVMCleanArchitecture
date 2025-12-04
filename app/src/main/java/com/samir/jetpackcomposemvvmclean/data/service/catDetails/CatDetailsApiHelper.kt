package com.samir.jetpackcomposemvvmclean.data.service.catDetails

import com.samir.jetpackcomposemvvmclean.data.models.SuccessResponse
import com.samir.jetpackcomposemvvmclean.data.models.catDetails.PostFavCatModel
import retrofit2.Response

interface CatDetailsApiHelper {
    suspend fun postFavCat(favCat: PostFavCatModel): Response<SuccessResponse>
    suspend fun deleteFavouriteCat(favouriteId: Int): Response<SuccessResponse>
}