package com.backbase.android.retail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.backbase.android.retail.databinding.BottomMenuScreenBinding

class BottomMenuScreen : Fragment() {

    private lateinit var _binding: BottomMenuScreenBinding
    private val binding get() = _binding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = BottomMenuScreenBinding.inflate(inflater, container, false)
        setUpNeoNavDesign()
        return binding.root
    }

    private fun setUpNeoNavDesign() {
        val navHost =
            childFragmentManager.findFragmentById(R.id.bottomMenuScreen_navHostFragmentContainer) as NavHostFragment
        val navController = navHost.navController
        binding.bottomMenu.menu.add(Menu.NONE, R.id.contacts_journey, 0, "Contacts")
        binding.bottomMenu.menu.add(Menu.NONE, R.id.contacts_journey1, 1, "Contacts1")
        binding.bottomMenu.setupWithNavController(navController)
        binding.bottomMenu.setOnItemSelectedListener { item ->
            NavigationUI.onNavDestinationSelected(
                item,
                navController
            )
        }
    }
}
