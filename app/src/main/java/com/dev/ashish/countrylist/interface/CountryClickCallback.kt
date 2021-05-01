package com.dev.ashish.countrylist.`interface`

import com.dev.ashish.countrylist.model.CountryResponseModel

/**
 *
 * Ashish Agrawal
 * Created on 01/05/21
 *
 */

/**
 * This interface will throw callback when user clicks any country row
 */
interface CountryClickCallback {
    fun onCountryClick(country : CountryResponseModel)
}