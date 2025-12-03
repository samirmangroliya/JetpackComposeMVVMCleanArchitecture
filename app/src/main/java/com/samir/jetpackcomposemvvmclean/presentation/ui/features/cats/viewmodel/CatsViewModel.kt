package com.samir.jetpackcomposemvvmclean.presentation.ui.features.cats.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samir.jetpackcomposemvvmclean.data.NetworkResult
import com.samir.jetpackcomposemvvmclean.data.models.CatDataModel
import com.samir.jetpackcomposemvvmclean.domain.usercase.cats.GetCatsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import javax.inject.Inject

@HiltViewModel
class CatsViewModel @Inject constructor(private val getCatsUseCase: GetCatsUseCase) : ViewModel() {

    private val stateFlow =
        MutableStateFlow<NetworkResult<List<CatDataModel>>>(NetworkResult.Loading)

    val state: StateFlow<NetworkResult<List<CatDataModel>>> = stateFlow

    init {
        getCatsData()
        getFavCatsData()
    }

    fun getCatsData() {
        viewModelScope.launch(Dispatchers.IO) {
            getCatsUseCase.getCats().collect {
                when (it) {
                    is NetworkResult.Success -> {
                        stateFlow.value = NetworkResult.Success(it.data)
                    }

                    is NetworkResult.Error -> {
                        stateFlow.value = NetworkResult.Error(it.message, it.code)
                    }

                    is NetworkResult.Loading -> {
                        stateFlow.value = NetworkResult.Loading
                    }
                }
            }

        }
    }

    fun getFavCatsData() {

    }

}