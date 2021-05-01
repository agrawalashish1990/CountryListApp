package com.dev.ashish.countrylist.adapter

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.dev.ashish.countrylist.R
import com.dev.ashish.countrylist.`interface`.CountryClickCallback
import com.dev.ashish.countrylist.extensions.CountryAppExtensions.loadImage
import com.dev.ashish.countrylist.model.CountryResponseModel
import kotlinx.android.synthetic.main.layout_row_country.view.*

//
// Created by Ashish on 01/05/21.
//

class CountryListAdapter(var context : Context, var list : MutableList<CountryResponseModel>, var callback : CountryClickCallback) : RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.layout_row_country,
                parent,
                false
            ),callback
        )
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(context,list.get(position))
    }

    override fun getItemCount(): Int {
      return list.size
    }

    class CountryViewHolder(private val view : View, private val callback: CountryClickCallback) : RecyclerView.ViewHolder(view){

        fun bind(context: Context,response: CountryResponseModel){
            view.tvName.setText(response.name)
            if(TextUtils.isEmpty(response.phoneCode)){
                view.tvCode.setText(response.code)
            }else{
                view.tvCode.setText(context.getString(R.string.data_country,response.code,response.phoneCode))
            }
            view.ivFlag.loadImage(response.code)
            view.setOnClickListener {
                callback.onCountryClick(response)
            }
        }
    }
}