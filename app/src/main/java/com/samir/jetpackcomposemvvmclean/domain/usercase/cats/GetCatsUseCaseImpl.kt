package com.samir.jetpackcomposemvvmclean.domain.usercase.cats

import com.samir.jetpackcomposemvvmclean.data.NetworkResult
import com.samir.jetpackcomposemvvmclean.data.models.CatDataModel
import com.samir.jetpackcomposemvvmclean.domain.repo.CatsRepository
import kotlinx.coroutines.flow.flow

class GetCatsUseCaseImpl(private val catsRepository: CatsRepository) : GetCatsUseCase {
    override suspend fun getCats() = flow {
        catsRepository.fetchCats().collect { catResponse ->
            when (catResponse) {
                is NetworkResult.Success -> {
                    val data = catResponse.data?.mapIndexed { index, cat ->
                        CatDataModel(
                            name =  "Name $index",
                            origin = "${cat.height}",
                            imageId = cat.id,
                            url = cat.url
                        )
                    }
                    emit(NetworkResult.Success(data))
                }

                is NetworkResult.Error -> {
                    emit(NetworkResult.Error(catResponse.message))
                }

                is NetworkResult.Loading -> {
                    emit(NetworkResult.Loading)
                }
            }
        }
    }

}