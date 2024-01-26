package com.backbase.cards_journey

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.backbase.card_use_case.domain.CustomCardUseCase
import com.backbase.cards_journey.databinding.FragmentCardsJourneyBinding
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject


class CardsJourney : Fragment() {

    private var _binding: FragmentCardsJourneyBinding? = null
    private val binding get() = _binding!!
    private val cardUseCase: CustomCardUseCase by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCardsJourneyBinding.inflate(inflater, container, false)
        Toast.makeText(requireContext(), "Cards Journey", Toast.LENGTH_SHORT).show()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                cardUseCase.getCards().fold(
                    onSuccess = {
                        Log.e("TAG", "onViewCreated: $it ", )
                        Toast.makeText(requireContext(), "${it.size}", Toast.LENGTH_SHORT).show()
                    },
                    onFailure = {
                        Toast.makeText(requireContext(), "Failed", Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }
    }

}