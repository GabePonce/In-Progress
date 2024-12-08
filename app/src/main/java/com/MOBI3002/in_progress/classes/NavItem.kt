package com.MOBI3002.in_progress.classes

/*
Author: Kendra MacKenzie & Samuel Cook & Gabriel Ponce
Date: December 02/24
Filename: NavItem.kt
Purpose: Final Project
 */

import androidx.compose.ui.graphics.vector.ImageVector

// dataclass that outlines the name of a screen and the icon a screen will have on the navbar itself
data class NavItem (
    val name : String, // Nav item name
    val icon : ImageVector // Nav item icon
)