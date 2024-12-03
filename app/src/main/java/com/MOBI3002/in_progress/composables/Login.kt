package com.MOBI3002.in_progress.composables


import android.content.Intent
import android.util.Patterns
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.MOBI3002.in_progress.R

/*
    Authors: Samuel Cook, Kendra MacKenzie, Gabe Ponce
    Date: October 29, 2024
    Project: Cake Ordering App
    Filename: RegisterActivity.kt
    Purpose: Class that handles the registration screen.
*/


@Composable
fun LoginScreen(context : ComponentActivity) {

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    // These variables keep track of the current error related to
    // each textfield.
    // These have values of " " by default to fix a problem I was
    // having where the page thought the fields were acceptable
    // before any input was entered
    var nameError by remember { mutableStateOf(" ") }
    var emailError by remember { mutableStateOf(" ") }
    var passwordError by remember { mutableStateOf(" ") }

    Column(
        Modifier
            .fillMaxSize()
            .background(Color(74, 170, 255))
    ) {

        Column() {

            Text(
                text = "Welcome to In-Progress",
                Modifier.padding(8.dp, 0.dp, 8.dp, 0.dp),
                fontSize = 30.sp,
                color = Color.White,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily.Default,
                textAlign = TextAlign.Center
            )

            Image(
                modifier = Modifier
                    .size(80.dp)
                    .align(Alignment.CenterHorizontally),
                painter = painterResource(id = R.drawable.duck_logo),
                contentDescription = "A small cake clip-art."
            )

        }

        Column(        Modifier
            .fillMaxSize()
            .background(Color(240, 240, 240))
        ) {


            Text(
                text = "Login",
                Modifier.padding(8.dp, 0.dp, 8.dp, 0.dp),
                fontSize = 30.sp,
                color = Color(165, 165, 165),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Default,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(15.dp))

            // Textfield for the user to enter a name.
            // The user can make their name anything,
            // it just can't be blank.
            OutlinedTextField(
                value = name,
                onValueChange = {
                    name = it
                    nameError =
                        if (it.isBlank()) "Please Enter Your Name Here." else ""
                },
                label = { Text("Name") }
            )
            // this checks to see if the user left the
            // name box blank. An error message will be
            // displayed if it is blank.
            if (nameError.isNotEmpty()) Text(
                text = nameError,
                color = MaterialTheme.colorScheme.error
            )

            Spacer(modifier = Modifier.height(15.dp))

            // Textfield for the user to enter an email.
            TextField(
                value = email,
                onValueChange = {
                    email = it
                    emailError =
                        if (!Patterns.EMAIL_ADDRESS.matcher(it)
                                .matches()
                        ) "Invalid email!" else ""
                    // This checks to make sure the email follows the email format.
                },
                label = { Text("Email") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                // This sets up the keyboard for email entry. It's nice for the user.
            )
            if (emailError.isNotEmpty()) Text(
                text = emailError,
                color = MaterialTheme.colorScheme.error
            )
            // If the user leaves the email empty, an error message will display.

            Spacer(modifier = Modifier.height(15.dp))

            // Textfield for password entry.
            TextField(
                value = password,
                onValueChange = {
                    password = it
                    val passwordPattern = Regex("^[a-zA-Z0-9@_]+$")
                    // regular expression to force the user to design their password in a specific pattern.

                    passwordError = when {
                        it.length < 8 -> "Password must be at least 8 characters long." // display error message if password is too short
                        !passwordPattern.matches(it) -> "Must use only letters, numbers, @, and _" // display error message if password deviates from the rules (regex)
                        else -> ""
                    }
                },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                // arranges the keyboard for password entry.
            )
            if (passwordError.isNotEmpty()) Text(
                text = passwordError,
                color = MaterialTheme.colorScheme.error
            )
            // Display an error message if the password field is empty.

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                val valid = nameError.isEmpty() && emailError.isEmpty() && passwordError.isEmpty()

                if (valid) { // navigate upon the button click only if the validation test passed.
//                    val navigate = Intent(this@RegisterActivity, LoginActivity::class.java)
//                    navigate.putExtra("email", email)
//                    navigate.putExtra("password", password)
//                    // send the values of email and password the user entered to the login activity.
//
//                    // I chose to keep this registration system simple because this assignment is only
//                    // a simple exercise. This is not secure, but this basic logic works for my requirements
//                    // in simulating a registration/login function.
//
//                    startActivity(navigate) // launch the next activity.
                }
            })

            {
                Text("Sign Up")
            }
        }
    }
}


//@Preview
//@Composable
//fun Display() {
//    LoginScreen()
//}
