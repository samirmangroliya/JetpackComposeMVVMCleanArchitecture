package com.samir.jetpackcomposemvvmclean.data.models.catDetails

import com.google.gson.annotations.SerializedName

data class PostFavCatModel(
    @SerializedName("image_id") val imageId: String? = null,
    @SerializedName("sub_id") val subId: String? = null
)