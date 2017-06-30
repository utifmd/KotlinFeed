package com.doricovix.utif.kotlinfeed

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.doricovix.utif.kotlinfeed.R

/**
 * Created by Utif on 5/21/2017.
 */
class DataList(private val context: Activity, internal var datas: List<Data>) : ArrayAdapter<Data>(context, R.layout.layout_item_data, datas) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val listViewItem = inflater.inflate(R.layout.layout_item_data, null, true)

        val textViewName = listViewItem.findViewById(R.id.textViewName) as TextView
        val textViewGenre = listViewItem.findViewById(R.id.textViewGenre) as TextView

        val artist = datas[position]
        textViewName.text = artist.name
        textViewGenre.text = artist.genre

        return listViewItem
    }
}
