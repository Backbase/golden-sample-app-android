package com.backbase.golden_sample_app.extend_journey.contacts.presentation.contactdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.backbase.golden_sample_app.databinding.FragmentCustomContactDetailBinding

class ContactDetailFragment : Fragment() {

    private var _binding: FragmentCustomContactDetailBinding? = null
    private val binding get() = _binding!!

    private val id by lazy {
        ContactDetailFragmentArgs.fromBundle(requireArguments()).id
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCustomContactDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.idSelected.text = "Contact ID selected: $id"
    }
}
