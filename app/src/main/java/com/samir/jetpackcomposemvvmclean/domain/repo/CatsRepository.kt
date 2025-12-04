package com.samir.jetpackcomposemvvmclean.domain.repo

import com.samir.jetpackcomposemvvmclean.data.NetworkResult
import com.samir.jetpackcomposemvvmclean.data.models.cats.CatListResponse
import com.samir.jetpackcomposemvvmclean.data.models.cats.FavouriteCatsResponse
import kotlinx.coroutines.flow.Flow

interface CatsRepository {
    suspend fun fetchCats(limit:Int=10): Flow<NetworkResult<List<CatListResponse>>>
    suspend fun fetchFavCats(subId:String): Flow<NetworkResult<List<FavouriteCatsResponse>>>
    suspend fun fetchTestFavouriteCats(subId: String): Flow<NetworkResult<List<FavouriteCatsResponse>>>
}