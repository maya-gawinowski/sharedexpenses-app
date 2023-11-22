package com.example.sharedexpensesapp.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.input.TextFieldValue

/**
 * Apparently read-only TextField is still not a feature in jetpack compose.
 * This is a work-around implementation with placing an invisible box overlaying the text field.
 * The implementation comes from
 * https://caelis.medium.com/jetpack-compose-datepicker-textfield-39808e42646a
 */
@Composable
fun ReadonlyTextField(
    modifier: Modifier = Modifier,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    onClick: () -> Unit,
    colors: TextFieldColors = TextFieldDefaults.colors(),
    label: @Composable () -> Unit
) {
    Box {
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier,
            label = label,
            colors = colors
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .alpha(0f)
                .clickable(onClick = onClick),
        )
    }
}
