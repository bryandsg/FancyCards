package com.hashiradev.fancycards.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hashiradev.fancycards.data.FancyCard
import com.hashiradev.fancycards.databinding.ItemFancyCardBinding

class FancyCardAdapter: ListAdapter<FancyCard, FancyCardAdapter.ViewHolder>(DiffCallback()) {

    var listenerShare: (View) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFancyCardBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }



    inner class ViewHolder(
        private val binding: ItemFancyCardBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FancyCard) {
            binding.tvName.text = item.name
            binding.tvPhone.text = item.phone
            binding.tvEmail.text = item.email
            binding.tvCompany.text = item.company
            binding.mcvFancyCard.setCardBackgroundColor(item.customBackgroundColor)
            binding.mcvFancyCard.setOnClickListener {
                listenerShare(it)
            }
        }
    }


}

class DiffCallback: DiffUtil.ItemCallback<FancyCard>() {
    override fun areItemsTheSame(oldItem: FancyCard, newItem: FancyCard) = oldItem == newItem
    override fun areContentsTheSame(oldItem: FancyCard, newItem: FancyCard) = oldItem.id == newItem.id
}