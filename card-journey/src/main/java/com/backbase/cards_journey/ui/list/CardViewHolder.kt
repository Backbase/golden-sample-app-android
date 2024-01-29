package com.backbase.cards_journey.ui.list

import androidx.recyclerview.widget.RecyclerView
import com.backbase.android.client.cardsclient2.model.CardItem
import com.backbase.cards_journey.databinding.InflateCardBinding

class CardViewHolder(private val binding: InflateCardBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: CardItem?) {
        binding.title.text = item?.name
        binding.cardNumber.text = getMaskedText(item)
    }

    private fun getMaskedText(item: CardItem?): String? {
        return item?.let {
            "**** **** **** ${it.maskedNumber}"
        }
    }
}