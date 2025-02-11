package com.bartburg.contacts_journey

import android.os.Bundle
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.bartburg.contacts_journey.data.service.MockContactsService
import kotlinx.coroutines.launch

class ContactsJourneyFragment : Fragment() {
    override fun onCreateView(
        inflater: android.view.LayoutInflater,
        container: android.view.ViewGroup?,
        savedInstanceState: Bundle?
    ): android.view.View {
        return ComposeView(requireContext()).apply {
            setContent {
                MaterialTheme {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        val scope = rememberCoroutineScope()
                        var text by remember { mutableStateOf("Loading") }
                        LaunchedEffect(true) {
                            scope.launch {
                                text = try {
                                    MockContactsService().greeting()
                                } catch (e: Exception) {
                                    e.localizedMessage ?: "error"
                                }
                            }
                        }
                        Text(text)
                    }
                }
            }
        }
    }
}