package com.samir.jetpackcomposemvvmclean.data.service

import com.samir.jetpackcomposemvvmclean.data.models.SuccessResponse
import com.samir.jetpackcomposemvvmclean.data.models.catDetails.PostFavCatModel
import com.samir.jetpackcomposemvvmclean.data.models.cats.CatListResponse
import com.samir.jetpackcomposemvvmclean.data.models.cats.FavouriteCatsResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface CatService {
    @GET("v1/images/search?size=small&has_breeds=true&order=RANDOM&page=0")
    suspend fun fetchCatsImages(
        @Query("limit") limit: Int
    ): Response<List<CatListResponse>>

    @GET("v1/favourites")
    suspend fun fetchFavouriteCats(
        @Query("sub_id") subId: String
    ): Response<List<FavouriteCatsResponse>>

    @POST("v1/favourites")
    suspend fun postFavouriteCat(
        @Body favCat: PostFavCatModel
    ): Response<SuccessResponse>

    @DELETE("v1/favourites/{favourite_id}")
    suspend fun deleteFavouriteCat(
        @Path("favourite_id") favouriteId: Int
    ): Response<SuccessResponse>

}