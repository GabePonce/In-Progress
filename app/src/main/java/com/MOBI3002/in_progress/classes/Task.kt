package com.MOBI3002.in_progress.classes

data class Task(
    val taskId: Int,
    val userId: Int,
    val taskDesc: String,
    val taskDueDate: String?
)
