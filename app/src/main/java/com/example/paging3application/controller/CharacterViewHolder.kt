package com.example.paging3application.controller

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.paging3application.R
import com.example.paging3application.models.CharacterData

class CharacterViewHolder (view: View) : RecyclerView.ViewHolder(view) {


    val imageView: ImageView = view.findViewById(R.id.imageView)
    val tvName: TextView = view.findViewById(R.id.tvName)
    val txtPosition: TextView = view.findViewById(R.id.txt_position)
    val tvDesc: TextView = view.findViewById(R.id.tvDesc)

    fun bind(data: CharacterData, position: Int) {
        tvName.text = data.name
        tvDesc.text = data.species

        val itemPosition = position+1
        txtPosition.text = itemPosition.toString()

        Glide.with(imageView)
            .load(data.image)
            .circleCrop()
            .into(imageView)

    }
}