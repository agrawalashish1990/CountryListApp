package com.dev.ashish.countrylist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dev.ashish.countrylist.network.ApiClient
import com.dev.ashish.countrylist.network.ApiInterface
import com.dev.ashish.countrylist.repository.DataRepository
import com.dev.ashish.countrylist.repository.RemoteDataSource

/**
 *
 * Ashish Agrawal
 * Created on 01/05/21
 *
 */
class ViewModelFactory (private val apiInterface: ApiInterface) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CountryViewModel::class.java)) {
            return CountryViewModel(DataRepository(RemoteDataSource(apiInterface))) as T
        }else if (modelClass.isAssignableFrom(ProvinceViewModel::class.java)) {
            return ProvinceViewModel(DataRepository(RemoteDataSource(apiInterface))) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}