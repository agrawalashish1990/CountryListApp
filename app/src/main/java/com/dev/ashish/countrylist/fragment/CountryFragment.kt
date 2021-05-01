package com.dev.ashish.countrylist.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev.ashish.countrylist.R
import com.dev.ashish.countrylist.`interface`.CountryClickCallback
import com.dev.ashish.countrylist.adapter.CountryListAdapter
import com.dev.ashish.countrylist.enum.Status
import com.dev.ashish.countrylist.model.CountryResponseModel
import com.dev.ashish.countrylist.network.ApiClient
import com.dev.ashish.countrylist.ui.HomeActivity
import com.dev.ashish.countrylist.utils.Constants
import com.dev.ashish.countrylist.viewmodel.CountryViewModel
import com.dev.ashish.countrylist.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_country.*


//
// Created by Ashish on 01/05/21.
//

class CountryFragment : Fragment() {

    private lateinit var countryViewModel: CountryViewModel
    private var listCountry: MutableList<CountryResponseModel> = mutableListOf()
    private lateinit var adapter: CountryListAdapter

    companion object {
        fun getInstance(): CountryFragment {
            return CountryFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_country, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setTitle()
        setUpAdapter()
        initViewModel()
        fetchCountryList()
    }

    //set toolbar title
    private fun setTitle() {
        (activity as HomeActivity).setupToolbar(getString(R.string.app_name), false)
    }

    //set recycler view adapter
    private fun setUpAdapter() {
        rvCountry.layoutManager = LinearLayoutManager(activity)
        rvCountry.addItemDecoration(
            DividerItemDecoration(
                activity,
                LinearLayoutManager.VERTICAL
            )
        )
        adapter = CountryListAdapter(
            requireContext(),
            listCountry,
            callback = object : CountryClickCallback {
                override fun onCountryClick(country: CountryResponseModel) {
                    openProvinceScreen(country)
                }
            })
        rvCountry.adapter = adapter
        adapter.notifyDataSetChanged()

    }

    /**
     * Open Province Fragment
     */
    private fun openProvinceScreen(country: CountryResponseModel) {
        var fragment = ProvinceFragment.getInstance()
        var bundle = Bundle()
        bundle.putParcelable(Constants.BUNDLE_COUNTRY_OBJ, country)
        fragment.arguments = bundle
        (activity as HomeActivity).showFragment(fragment)
    }

    //setup view model
    private fun initViewModel() {
        countryViewModel = ViewModelProvider(
            this,
            ViewModelFactory(ApiClient.getClient())
        ).get(CountryViewModel::class.java)
    }

    /**
     * Fetch country list
     */
    private fun fetchCountryList() {
        countryViewModel.fetchCountryList().observe(this, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        progressBar.visibility = View.GONE
                        resource.data?.let {
                            if (it.size > 0) {
                                listCountry.addAll(it)
                                adapter.notifyDataSetChanged()
                            } else {
                                showMessageInSnackBar()
                            }
                        }
                    }
                    Status.ERROR -> {
                        progressBar.visibility = View.GONE
                        showMessageInSnackBar(it.message)
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    /**
     * Show message in snack bar
     */
    fun showMessageInSnackBar(msg: String? = getString(R.string.plz_try_again)) {
        Snackbar.make(rootLayout, msg ?: getString(R.string.plz_try_again), 5000)
            .setAction(R.string.retry, View.OnClickListener {
                fetchCountryList()
            }).show()
    }

}