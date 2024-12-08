package com.MOBI3002.in_progress.composables
/*
Author: Kendra MacKenzie & Samuel Cook & Gabriel Ponce
Date: December 02/24
Filename: Tasks.kt
Purpose: Final Project
 */

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import com.MOBI3002.in_progress.classes.NavItem
import com.MOBI3002.in_progress.classes.Users
import com.MOBI3002.in_progress.data.DBHelper
import com.MOBI3002.in_progress.ui.theme.InProgressTheme


//The navbar activity which allows the user to go between composable screens
class NavBar : ComponentActivity() {
    private lateinit var dbHelper: DBHelper // dbhelper to pass into the sub composables

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dbHelper = DBHelper(this)

        //user name and passwords passed from the login screen to create a user object
        val email = intent.getStringExtra("email").toString()
        val pass = intent.getStringExtra("password").toString()

        //creates user object from the retrieve user function
        val user = dbHelper.retrieveUser(email, pass)

        setContent {
            InProgressTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavBarView(dbHelper, user)
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavBarView(dbHelper: DBHelper, user: Users?){
    // List of nav items for the nav bar
    val navItemList = listOf(
        NavItem(name = "Tasks", Icons.Default.List), // Tasks
        NavItem(name = "Add Task", Icons.Default.Add),
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
                .padding(innerPadding),selectedOption,
            //passing dbHelper and user here to pass them into the screens/composables that will be called
            dbHelper,
            user
            )
    }
}


// Design screen composable
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DesignScreen(modifier: Modifier = Modifier, selectedOption:Int, dbHelper: DBHelper, user: Users?){
    // Call required screen based on user selection
    when(selectedOption){
        0 -> Tasks(dbHelper, user) // Tasks screen
        1 -> AddTask(dbHelper, user) // Add new task screen
        2 -> Calendar(dbHelper, user) // Calendar screen
        3 -> Timer() // Timer screen
    }

}

