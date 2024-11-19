package com.backbase.cards_journey.impl.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.backbase.android.client.cardsclient2.model.CardItem
import com.backbase.cards_journey.impl.databinding.FragmentCardDetailsBinding
import com.backbase.cards_journey.impl.journeyScopedViewModel
import com.backbase.cards_journey.impl.utils.getMaskedText
import kotlinx.coroutines.launch
import org.koin.core.parameter.parametersOf

class CardDetailsFragment : Fragment() {

    private var _binding: FragmentCardDetailsBinding? = null
    private val args: CardDetailsFragmentArgs by navArgs()
    private val viewModel by journeyScopedViewModel<CardDetailsViewModel>(
        parameters = { parametersOf(args.cardId) }
    )
    private val binding get() = _binding!!

    init {
        collectState()
        collectEffect()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCardDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val params = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        )
        params.setMargins(0, 0, 0, 0)
        binding.card.cardLayout.layoutParams = params
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        viewModel.sendEvent(CardDetailsScreenEvent.GetCardDetails)
    }

    private fun collectState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    binding.loading.isVisible = state.isLoading
                    applyCardDetails(state.card)
                }
            }
        }
    }

    private fun collectEffect() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.effect.collect { effect ->
                    when (effect) {
                        CardDetailsScreenEffect.HandleErrorResponse ->
                            binding.error.isVisible =
                                true
                    }
                }
            }
        }
    }

    private fun applyCardDetails(card: CardItem?) {
        binding.details.isVisible = card != null
        if (card != null) {
            binding.error.isVisible = false
            binding.apply {
                subType.text = card.subType
                currency.text = card.currency
                onlineLimit.text = card.limits?.getOrNull(0)?.amount?.toString()
                atmLimit.text = card.limits?.getOrNull(1)?.amount?.toString()
                binding.card.title.text = card.name
                binding.card.cardNumber.text = getMaskedText(card)
            }
        }
    }
}
