package com.MOBI3002.in_progress.composables
/*
Author: Kendra MacKenzie & Samuel Cook & Gabriel Ponce
Date: December 02/24
Filename: Tasks.kt
Purpose: Final Project
 */

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.MOBI3002.in_progress.classes.NavItem
import com.MOBI3002.in_progress.ui.theme.InProgressTheme

class NavBar : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InProgressTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavBarView()
                }
            }
        }
    }
}

@Composable
fun NavBarView(){
    // Tasks variables
    // List of nav items for the nav bar
    val navItemList = listOf(
        NavItem(name = "Tasks", Icons.Default.List), // Tasks
        NavItem(name = "Calendar", Icons.Default.DateRange), // Calendar
        NavItem(name = "Timer", Icons.Default.PlayArrow), // Timer (No Clock)
    )
    // Mutable state variable to store selected screen
    var selectedOption by remember { mutableIntStateOf(value = 0) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(), // Fill max size
        bottomBar ={
            // Bottom nav bar
            NavigationBar {
                // For each nav item
                navItemList.forEachIndexed{ index, navItem ->
                    NavigationBarItem(
                        selected = selectedOption == index, // If item is selected
                        onClick = { selectedOption = index }, // Update selected item on click
                        icon= {
                            Icon(
                                imageVector = navItem.icon, // Assign icon for each item
                                contentDescription = "icon" // Item description
                            )
                        },
                        label = {
                            Text(
                                text = navItem.name // Item label
                            )
                        }
                    )
                }
            }
        }
    )
    { innerPadding ->
        // Call design screen composable
        DesignScreen(
            modifier = Modifier
                .padding(innerPadding),selectedOption)
    }
}

// Design screen composable
@Composable
fun DesignScreen(modifier: Modifier = Modifier, selectedOption:Int){
    // Call required screen based on user selection
    when(selectedOption){
        0 -> Tasks() // Tasks screen
        1 -> Calendar() // Calendar screen
        2 -> Timer() // Timer screen
    }

}

//@Preview
//@Composable
//fun previewDesignScreen(){
//    DesignScreen(selectedOption = )
//}

