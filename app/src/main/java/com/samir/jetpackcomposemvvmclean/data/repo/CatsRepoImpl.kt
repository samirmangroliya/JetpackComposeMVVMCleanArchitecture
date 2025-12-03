package com.samir.jetpackcomposemvvmclean.data.repo

import com.samir.jetpackcomposemvvmclean.data.NetworkResult
import com.samir.jetpackcomposemvvmclean.data.models.cats.FavouriteCatsResponse
import com.samir.jetpackcomposemvvmclean.data.service.cats.CatApiServiceHelper
import com.samir.jetpackcomposemvvmclean.domain.repo.CatsRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class CatsRepoImpl(private val catApiService: CatApiServiceHelper) : CatsRepository {
    override suspend fun fetchCats(limit: Int) =
        flow {
            emit(NetworkResult.Loading)
            with(catApiService.fetchCatsImages(limit)) {
                if (isSuccessful) {
                    emit(NetworkResult.Success(this.body()))
                } else {
                    emit(NetworkResult.Error(this.errorBody().toString(), this.code()))
                }
            }
        }.catch { emit(NetworkResult.Error(it.localizedMessage)) }

    override suspend fun fetchFavCats(subId: String) =
        flow<NetworkResult<List<FavouriteCatsResponse>>> {
            with(catApiService.fetchFavCats(subId)) {
                if (isSuccessful) {
                    emit(NetworkResult.Success(this.body()))
                } else {
                    emit(NetworkResult.Error(this.errorBody().toString()))
                }
            }
        }.catch { emit(NetworkResult.Error(it.localizedMessage)) }

    override suspend fun fetchTestFavouriteCats(subId: String) =
        flow {
            with(catApiService.fetchFavCats(subId)) {
                if (isSuccessful) {
                    emit(NetworkResult.Success(this.body()))
                } else {
                    emit(NetworkResult.Error(this.errorBody().toString()))
                }
            }
        }.catch { emit(NetworkResult.Error(it.localizedMessage)) }
}