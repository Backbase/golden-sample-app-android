package com.backbase.cards_journey.ui.list

import androidx.recyclerview.widget.RecyclerView
import com.backbase.android.client.cardsclient2.model.CardItem
import com.backbase.cards_journey.databinding.InflateCardBinding
import com.backbase.cards_journey.utils.getMaskedText

class CardViewHolder(private val binding: InflateCardBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: CardItem?, onclick: (CardItem) -> Unit) {
        binding.title.text = item?.name
        binding.cardNumber.text = getMaskedText(item)
        binding.root.setOnClickListener {
            if (item != null) {
                onclick(item)
            }
        }
    }


}