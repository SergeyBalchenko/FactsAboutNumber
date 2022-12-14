package com.example.factsaboutnumber.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.factsaboutnumber.databinding.RowNumberInfoBinding
import com.example.factsaboutnumber.db.entities.NumberInfo

class NumberAdapter(
    private val listener: NumberListener
): ListAdapter<NumberInfo, NumberAdapter.ViewHolder>(
    NumberInfoDiffUtil()
) {

    class ViewHolder(
        private val binding: RowNumberInfoBinding,
        private val listener: NumberListener
    ): RecyclerView.ViewHolder(binding.root) {

        fun onBind(numberInfo: NumberInfo) {
            binding.title.text = numberInfo.number.toString()
            binding.description.text = numberInfo.text
            binding.root.setOnClickListener {
                listener.onClick(numberInfo.number)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowNumberInfoBinding.inflate(inflater, parent, false)
        return ViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }
}

interface NumberListener {

    fun onClick(number: Int)
}