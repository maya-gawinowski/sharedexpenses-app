package com.example.sharedexpensesapp.ui.screens.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.toSize

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropdownIndexed(
    label: String,
    placeholder: String,
    items: List<String>,
    onValueChange: (textFieldValue: String) -> Unit,
    onIndexSelected: (selectedIndex: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var isDropDownExpanded by remember { mutableStateOf(false) }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    var selectedIndex by remember { mutableIntStateOf(-1) }
    var displayedValue by remember { mutableStateOf("") }

    val icon =
        if (isDropDownExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown
    ExposedDropdownMenuBox(
        expanded = isDropDownExpanded,
        onExpandedChange = { isDropDownExpanded = !isDropDownExpanded },
        modifier = modifier
    ) {
        OutlinedTextField(
            value = displayedValue,
            onValueChange = onValueChange,
            label = { Text(text = label) },
            placeholder = { Text(text = placeholder)},
            readOnly = true,
            modifier = Modifier
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                }
                .clickable {
                    isDropDownExpanded = !isDropDownExpanded
                },
            trailingIcon = {
                Icon(icon, "Description")
            },
        )
        DropdownMenu(
            modifier = Modifier
                .width(with(LocalDensity.current) { textFieldSize.width.toDp() }),
            expanded = isDropDownExpanded,
            onDismissRequest = { isDropDownExpanded = false }) {
            items.forEachIndexed { index, item ->
                DropdownMenuItem(
                    onClick = {
                        onIndexSelected(index)
                        selectedIndex = index
                        displayedValue = items[selectedIndex]
                        isDropDownExpanded = false
                    }) {
                    Text(text = item)
                }
            }
        }
    }
}