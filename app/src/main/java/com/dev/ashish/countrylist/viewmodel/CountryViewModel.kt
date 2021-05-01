package com.dev.ashish.countrylist.viewmodel

import androidx.lifecycle.*
import com.dev.ashish.countrylist.model.CountryResponseModel
import com.dev.ashish.countrylist.model.ProvinceResponseModel
import com.dev.ashish.countrylist.network.ApiClient
import com.dev.ashish.countrylist.repository.DataRepository
import com.dev.ashish.countrylist.repository.RemoteDataSource
import com.dev.ashish.countrylist.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

//
// Created by Ashish on 01/05/21.
//

class CountryViewModel(private val repository: DataRepository) : ViewModel() {

    private var countryList : List<CountryResponseModel> = listOf()


    /**
     * This method will fetch the countryList
     */
    fun fetchCountryList():LiveData<Resource<List<CountryResponseModel>>>{

        return liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                if(countryList.isEmpty()){
                    countryList = repository.getCountryList();
                }
                emit(Resource.success(data = countryList))
            } catch (e: Exception) {
                emit(Resource.error(data = null, e.localizedMessage))
            }
        }
    }


}