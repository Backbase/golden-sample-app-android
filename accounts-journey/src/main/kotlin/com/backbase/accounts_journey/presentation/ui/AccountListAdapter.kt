package com.backbase.accounts_journey.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.backbase.accounts_journey.databinding.AccountListHeaderBinding
import com.backbase.accounts_journey.databinding.AccountListItemBinding
import com.backbase.accounts_journey.presentation.model.AccountHeaderUiModel
import com.backbase.accounts_journey.presentation.model.AccountUiModel
import com.backbase.accounts_journey.presentation.model.ListItem
import com.backbase.accounts_journey.presentation.model.ViewType
import java.io.InvalidClassException

/**
 * A list adapter to show the account list with headers.
 *
 * Created by Backbase R&D B.V on 04/10/2023.
 */
class AccountListAdapter(
    private val onClick: (String) -> Unit
) : ListAdapter<ListItem, RecyclerView.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder = when (viewType) {
            ViewType.HEADER.value -> {
                val binding = AccountListHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                HeaderViewHolder(binding)
            }

            ViewType.ITEM.value -> {
                val binding = AccountListItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ListItemViewHolder(binding)
            }

            else -> throw InvalidClassException("Value must be from ViewType")
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val uiModel = getItem(position)
        holder.apply {
            when (uiModel.viewType) {
                ViewType.HEADER -> {
                    uiModel as AccountHeaderUiModel
                    holder as HeaderViewHolder
                    holder.binding.apply {
                        accountHeader.text = uiModel.name
                    }
                }

                ViewType.ITEM -> {
                    uiModel as AccountUiModel
                    holder as ListItemViewHolder
                    holder.binding.apply {
                        accountName.text = uiModel.name
                        accountBalance.text = uiModel.balance
                        accountState.text = uiModel.state
                        accountIcon.icon = holder.itemView.context.getDrawable(uiModel.icon)
                    }
                    itemView.setOnClickListener {
                        uiModel.id?.let(onClick)
                    }
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return currentList[position].viewType.value
    }

    inner class HeaderViewHolder(val binding: AccountListHeaderBinding) :
        RecyclerView.ViewHolder(binding.root)

    inner class ListItemViewHolder(val binding: AccountListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    class DiffCallback : DiffUtil.ItemCallback<ListItem>() {
        override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
            return oldItem == newItem
        }
    }
}
