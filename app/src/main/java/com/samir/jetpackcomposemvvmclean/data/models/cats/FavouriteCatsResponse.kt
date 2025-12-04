package com.samir.jetpackcomposemvvmclean.data.models.cats

import com.google.gson.annotations.SerializedName

data class FavouriteCatsResponse(
    @SerializedName("created_at") val createdAt: String? = null,
    val id: Int? = null,
    val image: Image? = null,
    @SerializedName("image_id")
    val imageId: String,
    @SerializedName("sub_id")
    val subId: String,
    @SerializedName("user_id")
    val userId: String
)

data class Image(
    val id: String,
    val url: String
)