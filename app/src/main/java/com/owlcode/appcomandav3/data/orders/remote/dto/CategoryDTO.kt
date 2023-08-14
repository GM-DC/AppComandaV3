package com.owlcode.appcomandav3.data.orders.remote.dto

import com.owlcode.appcomandav3.domain.orders.model.CategoryModel
import com.google.gson.annotations.SerializedName

data class CategoryDTO(
    @SerializedName("Nombre") val nameCategoria: String,
    @SerializedName("Numero") val idCategoria: String
)

fun CategoryDTO.toCategoryModel(): CategoryModel {
    return  CategoryModel(
        nameCategoria = nameCategoria,
        idCategoria = idCategoria
    )
}