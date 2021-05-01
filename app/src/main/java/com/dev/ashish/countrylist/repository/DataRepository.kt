package com.dev.ashish.countrylist.repository

import androidx.lifecycle.LiveData
import com.dev.ashish.countrylist.model.CountryResponseModel
import com.dev.ashish.countrylist.model.ProvinceResponseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 *
 * Ashish Agrawal
 * Created on 01/05/21
 *
 */
class DataRepository constructor(private val remoteDataSource: RemoteDataSource) {

    /**
     * Here we can handle logic to return from local cache/DB or from remote server
     *
     * For our sample this is dependent on server only
     */
    suspend fun getCountryList(): List<CountryResponseModel> {
        return remoteDataSource.fetchCountryList()
    }

    suspend fun getProvinceList(countryId: Int): List<ProvinceResponseModel> {
        return remoteDataSource.fetchProvinceList(countryId)
    }
}