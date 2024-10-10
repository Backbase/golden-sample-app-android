package com.backbase.cards_journey.impl.ui.list

import androidx.recyclerview.widget.RecyclerView
import com.backbase.android.client.cardsclient2.model.CardItem
import com.backbase.cards_journey.impl.databinding.InflateCardBinding
import com.backbase.cards_journey.impl.utils.getMaskedText

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