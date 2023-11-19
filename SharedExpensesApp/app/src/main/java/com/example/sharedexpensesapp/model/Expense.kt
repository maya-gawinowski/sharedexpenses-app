package com.example.sharedexpensesapp.model

import kotlinx.serialization.Serializable

@Serializable
data class Expense(
    val payerId:String,
    val participantsIds:List<String>,
    val amount:Double,
    val description:String,
    val date: String,
)
