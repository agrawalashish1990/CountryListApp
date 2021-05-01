package com.dev.ashish.countrylist.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

//
// Created by Ashish on 15/03/21.
//

@Parcelize
data class CountryResponseModel(
    @SerializedName("ID")
    val id: Int,
    @SerializedName("Name")
    val name: String,
    @SerializedName("Code")
    val code: String,
    @SerializedName("PhoneCode")
    val phoneCode: String
) : Parcelable

data class ProvinceResponseModel(
    @SerializedName("ID")
    val id: Int,
    @SerializedName("Name")
    val name: String,
    @SerializedName("Code")
    val code: String,
    @SerializedName("CountryCode")
    val countryCode: String
)



