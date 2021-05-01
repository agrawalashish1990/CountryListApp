package com.dev.ashish.countrylist.repository

import com.dev.ashish.countrylist.network.ApiInterface

/**
 *
 * Ashish Agrawal
 * Created on 01/05/21
 *
 */
class RemoteDataSource constructor(val apiInterface: ApiInterface) {

    suspend fun fetchCountryList() = apiInterface.getCountryList()

    suspend fun fetchProvinceList(countryId : Int) = apiInterface.getProvinceList(countryId)
}