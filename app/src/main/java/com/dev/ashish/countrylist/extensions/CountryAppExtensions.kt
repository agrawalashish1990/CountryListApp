package com.dev.ashish.countrylist.extensions

import android.widget.ImageView
import com.dev.ashish.countrylist.R

/**
 *
 * Ashish Agrawal
 * Created on 01/05/21
 *
 */
object CountryAppExtensions {

    /**
     * This extension method will load icon from URL
     */
    fun ImageView.loadImage(code: String){

        /**
         * It is not able to load image from "https://github.com/hampusborgos/country-flags/blob/main/png250px"
         *
         * so we are using local images
         */

//        Picasso.get()
//            .load(Constants.IMAGE_BASE_URL.plus(code.toLowerCase()).plus(".png"))
//            .placeholder(R.mipmap.ic_launcher)
//            .error(R.mipmap.ic_launcher)
//            .into(this);
        if(code.toLowerCase().equals("do")){
            setImageResource(R.drawable.do_1)
        }else{
            this.setImageResource(context.getResources()
                .getIdentifier(code.toLowerCase(), "drawable", context.getPackageName()))
        }
    }

}