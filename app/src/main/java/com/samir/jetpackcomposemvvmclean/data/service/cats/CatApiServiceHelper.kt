package com.samir.jetpackcomposemvvmclean.data.service.cats

import com.samir.jetpackcomposemvvmclean.data.models.cats.CatListResponse
import com.samir.jetpackcomposemvvmclean.data.models.cats.FavouriteCatsResponse
import retrofit2.Response


interface CatApiServiceHelper {
    suspend fun fetchCatsImages(limit:Int): Response<List<CatListResponse>>
    suspend fun fetchFavCats(subId:String): Response<List<FavouriteCatsResponse>>
}