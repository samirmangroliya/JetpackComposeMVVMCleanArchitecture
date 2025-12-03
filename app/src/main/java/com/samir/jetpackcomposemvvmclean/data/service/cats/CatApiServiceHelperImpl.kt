package com.samir.jetpackcomposemvvmclean.data.service.cats

import com.samir.jetpackcomposemvvmclean.data.models.cats.CatListResponse
import com.samir.jetpackcomposemvvmclean.data.models.cats.FavouriteCatsResponse
import com.samir.jetpackcomposemvvmclean.data.service.CatService
import retrofit2.Response


class CatApiServiceHelperImpl(private val service: CatService) : CatApiServiceHelper {
    override suspend fun fetchCatsImages(limit: Int): Response<List<CatListResponse>> {
        return service.fetchCatsImages(limit)
    }

    override suspend fun fetchFavCats(subId: String): Response<List<FavouriteCatsResponse>> {
        return service.fetchFavouriteCats(subId)
    }
}