package com.dev.ashish.countrylist.network

import com.dev.ashish.countrylist.model.CountryResponseModel
import com.dev.ashish.countrylist.model.ProvinceResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

//
// Created by Ashish on 01/05/21.
//

interface ApiInterface {

    @GET("country")
    suspend fun getCountryList(): List<CountryResponseModel>

    @GET("country/{ID}/province")
    suspend fun getProvinceList(@Path("ID") countryId: Int): List<ProvinceResponseModel>
}