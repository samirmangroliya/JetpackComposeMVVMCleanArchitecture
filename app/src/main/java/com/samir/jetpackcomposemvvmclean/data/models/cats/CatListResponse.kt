package com.samir.jetpackcomposemvvmclean.data.models.cats

data class CatListResponse(
    val breed: List<Breed>?,
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
)

