package com.MOBI3002.in_progress

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.MOBI3002.in_progress.ui.theme.InProgressTheme
import com.MOBI3002.in_progress.composables.LoginScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InProgressTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Login(this@MainActivity) /// Call the login composable
                }
            }
        }
    }
}

// Composable for calling the login screen to be displayed when the application is opened
@Composable
fun Login(context : ComponentActivity){
    InProgressTheme{
        LoginScreen(context) // Call the login screen composable
    }
}

