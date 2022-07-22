package com.example.paging3application.controller

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.paging3application.R
import com.example.paging3application.models.CharacterData
import javax.inject.Inject

//class ForecastAdapter @Inject constructor() : RecyclerView.Adapter<ForecastViewHolder>(){


class CharacterAdapter @Inject constructor():PagingDataAdapter<CharacterData , CharacterViewHolder>(DiffUtilCallBack()) {



    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(getItem(position)!! , position)
        holder.setOnListHolderListener(listener, getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        return CharacterViewHolder(view)
    }



    class DiffUtilCallBack: DiffUtil.ItemCallback<CharacterData>() {
        override fun areItemsTheSame(oldItem: CharacterData, newItem: CharacterData): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: CharacterData, newItem: CharacterData): Boolean {
            return oldItem.name == newItem.name
                    && oldItem.species == newItem.species
        }

    }


    private var listener: CharacterItemInteraction? = null
    fun setListener(listener: CharacterItemInteraction) {
        this.listener = listener
    }
}