package com.samir.jetpackcomposemvvmclean.data.repo

import com.samir.jetpackcomposemvvmclean.data.NetworkResult
import com.samir.jetpackcomposemvvmclean.data.models.SuccessResponse
import com.samir.jetpackcomposemvvmclean.data.models.catDetails.PostFavCatModel
import com.samir.jetpackcomposemvvmclean.data.service.catDetails.CatDetailsApiHelper
import com.samir.jetpackcomposemvvmclean.domain.repo.CatDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class CatDetailsRepoImpl(private val catDetailsApiService: CatDetailsApiHelper) :
    CatDetailsRepository {
    override suspend fun postFavouriteCat(favCat: PostFavCatModel) =
        flow {
            emit(NetworkResult.Loading)
            with(catDetailsApiService.postFavCat(favCat)) {
                if (isSuccessful) {
                    emit(NetworkResult.Success(this.body()))
                } else {
                    emit(NetworkResult.Error(this.errorBody().toString(), this.code()))
                }
            }
        }.catch { emit(NetworkResult.Error(it.localizedMessage)) }

    override suspend fun deleteFavouriteCat(
        imageId: String,
        favouriteId: Int
    ) = flow {
        with(catDetailsApiService.deleteFavouriteCat(favouriteId)) {
            if (isSuccessful) {
                emit(NetworkResult.Success(this.body()))
            } else {
                emit(NetworkResult.Error(this.errorBody().toString(), this.code()))
            }
        }
    }.catch { emit(NetworkResult.Error(it.localizedMessage)) }


}