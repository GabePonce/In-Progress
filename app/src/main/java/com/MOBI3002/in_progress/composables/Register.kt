package com.MOBI3002.in_progress.composables
/*
Author: Kendra MacKenzie & Samuel Cook & Gabriel Ponce
Date: December 02/24
Filename: Register.kt
Purpose: Final Project
 */

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.MOBI3002.in_progress.ui.theme.InProgressTheme

class Register : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InProgressTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RegisterScreen()
                }
            }
        }
    }
}

@Composable
fun RegisterScreen(){

}