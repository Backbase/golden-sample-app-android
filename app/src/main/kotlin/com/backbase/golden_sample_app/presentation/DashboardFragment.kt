package com.backbase.golden_sample_app.presentation

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.backbase.android.design.header.TabHeaderFragment
import com.backbase.golden_sample_app.R
import com.backbase.golden_sample_app.presentation.header.TabListConfigurationProvider

class DashboardFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tabListConfigurationProvider = TabListConfigurationProvider(requireContext())

        findNavController().navigate(
            resId = R.id.action_dashboard_to_tab_header_dashboard,
            args = bundleOf(TabHeaderFragment.TabListConfigurationKey to tabListConfigurationProvider.dashboardTabList())
        )
    }
}
