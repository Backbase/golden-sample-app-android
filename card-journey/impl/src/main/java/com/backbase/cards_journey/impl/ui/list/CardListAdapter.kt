package com.backbase.cards_journey.impl.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.backbase.android.client.cardsclient2.model.CardItem
import com.backbase.cards_journey.impl.databinding.InflateCardBinding

class CardListAdapter(val onclick: (CardItem) -> Unit) :
    ListAdapter<CardItem, CardViewHolder>(BillsDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return CardViewHolder(
            InflateCardBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(getItem(position), onclick)
    }

    private class BillsDiffCallBack : DiffUtil.ItemCallback<CardItem>() {
        override fun areItemsTheSame(
            oldItem: CardItem, newItem: CardItem
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: CardItem, newItem: CardItem
        ): Boolean = oldItem == newItem
    }
}
