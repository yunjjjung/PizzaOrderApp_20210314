package com.thejoeun.pizzaorderapp_20210314.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.thejoeun.pizzaorderapp_20210314.R
import com.thejoeun.pizzaorderapp_20210314.datas.Store

class StoreAdapter(
    val mContext : Context,
    val resId : Int,
    val mList : ArrayList<Store>) : ArrayAdapter<Store>(mContext, resId, mList) {

    val inf = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var tempRow = convertView
        if(tempRow == null) {
            tempRow = inf.inflate(R.layout.store_list_item, null)
        }

        val row = tempRow!!

        val storeData = mList[position]

        val logoImg = row.findViewById<ImageView>(R.id.logoImg)
        val storeNameTxt = row.findViewById<TextView>(R.id.storeNameTxt)

        storeNameTxt.text = storeData.storeName

        Glide.with(mContext).load(storeData.logoUri).into(logoImg)

        return row
    }
}