package com.backbase.android.journey.contacts.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ContactDetailField(label: String, value: String) {
    Row {
        Text(label, fontWeight = FontWeight.Bold)
        Text(value, Modifier.padding(start = 8.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun ContactDetailFieldPreview() {
    ContactDetailField(
        label = "Test Label",
        value = "Test Value"
    )
}