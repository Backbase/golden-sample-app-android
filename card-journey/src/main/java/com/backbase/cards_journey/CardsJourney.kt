package com.backbase.cards_journey

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.backbase.cards_journey.databinding.FragmentCardsJourneyBinding


class CardsJourney : Fragment() {

    private var _binding: FragmentCardsJourneyBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCardsJourneyBinding.inflate(inflater, container, false)
        return binding.root
    }

}