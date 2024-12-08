package com.MOBI3002.in_progress.classes

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class Task(
    val taskId: Int,
    val userId: Int,
    val taskDesc: String,
    val taskDueDate: String?,
    var isChecked: MutableState<Boolean> = mutableStateOf(false)
)
