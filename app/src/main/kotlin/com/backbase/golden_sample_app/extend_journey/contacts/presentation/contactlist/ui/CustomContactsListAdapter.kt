package com.backbase.golden_sample_app.extend_journey.contacts.presentation.contactlist.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.backbase.golden_sample_app.databinding.CustomContactsListItemBinding
import com.backbase.golden_sample_app.extend_journey.contacts.presentation.contactlist.model.ContactUiModel

class CustomContactsListAdapter(
    private val onClick: (String) -> Unit
) : ListAdapter<ContactUiModel, CustomContactsListAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CustomContactsListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val uiModel = getItem(position)
        holder.binding.apply {
            contactName.text = uiModel.name
            contactNumber.text = uiModel.number
            avatar.text = uiModel.avatarName

            val background = DrawableCompat.wrap(avatar.background)
            DrawableCompat.setTint(background, uiModel.color)
            avatar.setBackgroundDrawable(background)
        }
        holder.itemView.setOnClickListener {
            onClick(uiModel.id)
        }
    }

    inner class ViewHolder(val binding: CustomContactsListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    class DiffCallback : DiffUtil.ItemCallback<ContactUiModel>() {
        override fun areItemsTheSame(oldItem: ContactUiModel, newItem: ContactUiModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ContactUiModel, newItem: ContactUiModel): Boolean {
            return oldItem == newItem
        }
    }
}
