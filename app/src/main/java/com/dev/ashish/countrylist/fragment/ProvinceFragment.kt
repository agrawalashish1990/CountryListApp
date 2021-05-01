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
import com.dev.ashish.countrylist.adapter.ProvinceListAdapter
import com.dev.ashish.countrylist.enum.Status
import com.dev.ashish.countrylist.model.CountryResponseModel
import com.dev.ashish.countrylist.model.ProvinceResponseModel
import com.dev.ashish.countrylist.network.ApiClient
import com.dev.ashish.countrylist.ui.HomeActivity
import com.dev.ashish.countrylist.utils.Constants
import com.dev.ashish.countrylist.viewmodel.ProvinceViewModel
import com.dev.ashish.countrylist.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_province.*

//
// Created by Ashish on 01/05/21.
//

class ProvinceFragment : Fragment() {

    private lateinit var provinceViewModel: ProvinceViewModel
    private lateinit var adapter: ProvinceListAdapter
    private var countryObj : CountryResponseModel? = null
    private var listProvince : MutableList<ProvinceResponseModel> = mutableListOf()


    companion object {
        fun getInstance(): ProvinceFragment {
            return ProvinceFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        countryObj = arguments?.getParcelable(Constants.BUNDLE_COUNTRY_OBJ)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_province, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        countryObj?.let {
            setTitle()
            setUpComponent()
            fetchProvinceList()
        }?: run {
            Toast.makeText(context,getString(R.string.plz_try_again),Toast.LENGTH_SHORT).show()
            fragmentManager?.popBackStack()
        }
    }

    private fun setTitle() {
        (activity as HomeActivity).setupToolbar(getString(R.string.country_province_list,countryObj?.name),true)
    }

    /**
     * Setup view model & recycler view adapter
     */
    private fun setUpComponent(){
        provinceViewModel =  ViewModelProvider(this, ViewModelFactory(ApiClient.getClient())).get(ProvinceViewModel::class.java)

        rvProvince.layoutManager = LinearLayoutManager(activity)
        rvProvince.addItemDecoration(DividerItemDecoration(activity, LinearLayoutManager.VERTICAL))
        adapter = ProvinceListAdapter(requireContext(), listProvince)
        rvProvince.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    /**
     * Fetch province list
     */
    private fun fetchProvinceList(){
        provinceViewModel.fetchProvinceList(countryObj?.id).observe(this,{
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        progressBar.visibility = View.GONE
                        resource.data?.let {
                            if(it.size > 0) {
                                listProvince.clear()
                                listProvince.addAll(it)
                                adapter.notifyDataSetChanged()
                            }else{
                                showMessageInSnackBar(null)
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
    fun showMessageInSnackBar(msg : String?){
        Snackbar.make(rootLayout, msg?:getString(R.string.plz_try_again), 5000).setAction(R.string.retry, View.OnClickListener{
            fetchProvinceList()
        } ).show()
    }
}