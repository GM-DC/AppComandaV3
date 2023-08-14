package com.owlcode.appcomandav3.data.orders.remote

import com.owlcode.appcomandav3.data.orders.remote.dto.CategoryDTO
import retrofit2.http.GET

interface CategoryApi {
    @GET("/api/TablasBasicas/Detail?filter=codigo eq 'CATE_PROD' and referencia4 eq 'R' and ESTADO eq '1'&select=codigo,nombre,numero&orderby=nombre asc")
    suspend fun getCategory() : List<CategoryDTO>
}