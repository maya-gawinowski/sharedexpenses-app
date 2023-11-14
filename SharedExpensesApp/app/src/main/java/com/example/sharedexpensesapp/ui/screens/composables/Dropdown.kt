package com.example.sharedexpensesapp.ui.screens.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
fun Dropdown(
    selectedItem: String,
    items: List<String>,
    onValueChange: (textFieldValue: String) -> Unit,
    onItemSelected: (selectedItem: String) -> Unit,
    modifier: Modifier = Modifier
) {
    var isDropDownExpanded by remember { mutableStateOf(false) }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    val icon =
        if (isDropDownExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown
    ExposedDropdownMenuBox(
        expanded = isDropDownExpanded,
        onExpandedChange = { isDropDownExpanded = !isDropDownExpanded },
        modifier = modifier
    ) {
        OutlinedTextField(
            value = selectedItem,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                }
                .clickable {
                    isDropDownExpanded = !isDropDownExpanded
                },
            label = { Text(text = "Choose a group") },
            trailingIcon = {
                Icon(icon, "Description")
            }
        )
        DropdownMenu(
            modifier = Modifier
                .width(with(LocalDensity.current) { textFieldSize.width.toDp() }),
            expanded = isDropDownExpanded,
            onDismissRequest = { isDropDownExpanded = false }) {
            items.forEach { item ->
                DropdownMenuItem(onClick = {
                    onItemSelected(item)
                    isDropDownExpanded = false
                }) {
                    Text(text = item)
                }
            }
        }

    }
}