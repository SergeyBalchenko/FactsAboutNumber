package com.example.factsaboutnumber.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.factsaboutnumber.db.entities.NumberInfo

class NumberInfoDiffUtil: DiffUtil.ItemCallback<NumberInfo>() {

    override fun areItemsTheSame(oldItem: NumberInfo, newItem: NumberInfo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: NumberInfo, newItem: NumberInfo): Boolean {
        return oldItem == newItem
    }
}