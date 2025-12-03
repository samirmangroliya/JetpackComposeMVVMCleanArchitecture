package com.samir.jetpackcomposemvvmclean.data.service.catDetails

import com.samir.jetpackcomposemvvmclean.data.models.SuccessResponse
import com.samir.jetpackcomposemvvmclean.data.models.catDetails.PostFavCatModel
import com.samir.jetpackcomposemvvmclean.data.service.CatService
import retrofit2.Response

class CatDetailsApiHelperImpl(private val service: CatService) : CatDetailsApiHelper {
    override suspend fun postFavCat(favCat: PostFavCatModel): Response<SuccessResponse> {
        return service.postFavouriteCat(favCat)
    }

    override suspend fun deleteFavouriteCat(favouriteId: Int): Response<SuccessResponse> {
        return service.deleteFavouriteCat(favouriteId)
    }
}