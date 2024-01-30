package com.backbase.cards_journey.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.backbase.android.client.cardsclient2.model.CardItem
import com.backbase.cards_journey.R
import com.backbase.cards_journey.databinding.FragmentCardListBinding
import com.backbase.cards_journey.journeyScopedViewModel
import kotlinx.coroutines.launch


class CardListFragment : Fragment() {

    private val viewModel by journeyScopedViewModel<CardListViewModel>()

    private var _binding: FragmentCardListBinding? = null
    private val binding get() = _binding!!
    private lateinit var cardsAdapter: CardListAdapter

    init {
        collectState()
        collectEffect()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCardListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.sendEvent(CardListScreenEvent.GetCardList)

        cardsAdapter = CardListAdapter(::onclick)
        binding.pager.apply {
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            offscreenPageLimit = 2
//            adjustViewPager(this)
            adapter = cardsAdapter
        }
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun onclick(card: CardItem) {
        findNavController()
            .navigate(CardListFragmentDirections.actionCardListFragmentToCardDetailsFragment(card.id))
    }

    private fun collectState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    binding.loading.isVisible = state.isLoading
                    if (state.cards != null) {
                        binding.error.isVisible = false
                        cardsAdapter.submitList(state.cards)
                    }
                }
            }
        }
    }

    private fun collectEffect() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.effect.collect { effect ->
                    when (effect) {
                        CardListScreenEffect.HandleErrorResponse -> binding.error.isVisible = true
                    }
                }
            }
        }
    }

    private fun adjustViewPager(viewPager: ViewPager2) {
        val pageTranslationX = resources.getDimension(R.dimen.margin_200)
        val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
            page.translationX = -pageTranslationX * position
        }
        viewPager.setPageTransformer(pageTransformer)
    }

}