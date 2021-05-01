package com.dev.ashish.countrylist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.dev.ashish.countrylist.model.CountryResponseModel
import com.dev.ashish.countrylist.model.ProvinceResponseModel
import com.dev.ashish.countrylist.network.ApiClient
import com.dev.ashish.countrylist.repository.DataRepository
import com.dev.ashish.countrylist.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

//
// Created by Ashish on 01/05/21.
//

class ProvinceViewModel(private val repository: DataRepository) : ViewModel() {

     lateinit var provinceList : List<ProvinceResponseModel>

    /**
     * This method will fetch the province List
     */
    fun fetchProvinceList(countryId : Int?) = liveData(Dispatchers.IO) {
        countryId?.let {
            emit(Resource.loading(data = null))
            try {
                if(!::provinceList.isInitialized){
                    provinceList = repository.getProvinceList(countryId);
                }
                emit(Resource.success(data = provinceList))
            } catch (e: Exception) {
                emit(Resource.error(data = null, e.localizedMessage))
            }
        }
    }

}