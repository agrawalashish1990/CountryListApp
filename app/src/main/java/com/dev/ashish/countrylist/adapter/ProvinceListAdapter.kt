package com.dev.ashish.countrylist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dev.ashish.countrylist.R
import com.dev.ashish.countrylist.model.ProvinceResponseModel
import kotlinx.android.synthetic.main.layout_row_province.view.*

//
// Created by Ashish on 01/05/21.
//

class ProvinceListAdapter(var context : Context, var list : MutableList<ProvinceResponseModel>) : RecyclerView.Adapter<ProvinceListAdapter.ProvinceViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProvinceViewHolder {
        return ProvinceViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.layout_row_province,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProvinceViewHolder, position: Int) {
        holder.bind(context,list.get(position))
    }

    override fun getItemCount(): Int {
      return list.size
    }

    class ProvinceViewHolder(val view : View) : RecyclerView.ViewHolder(view){
        fun bind(context: Context,response: ProvinceResponseModel){
            view.tvName.setText(context.getString(R.string.data_province, response.name, response.code))
        }
    }
}