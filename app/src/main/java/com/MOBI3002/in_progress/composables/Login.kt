package com.MOBI3002.in_progress.composables


import android.content.Intent
import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import androidx.core.app.ComponentActivity
import androidx.core.view.KeyEventDispatcher.Component
import com.MOBI3002.in_progress.R
import kotlin.coroutines.jvm.internal.CompletedContinuation.context

/*
    Authors: Samuel Cook, Kendra MacKenzie, Gabe Ponce
    Date: October 29, 2024
    Project: Cake Ordering App
    Filename: RegisterActivity.kt
    Purpose: Class that handles the registration screen.
*/

@Composable
fun LoginScreen(context: ComponentActivity) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var emailError by remember { mutableStateOf(" ") }
    var passwordError by remember { mutableStateOf(" ") }

    Column(
        Modifier
            .fillMaxSize()
            .background(Color(74, 170, 255))
    ) {

        Column(modifier = Modifier.padding(32.dp)) {

            Text(
                text = "Welcome to In-Progress",
                Modifier.padding(8.dp, 0.dp, 8.dp, 0.dp),
                fontSize = 35.sp,
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
                contentDescription = "DUCK"
            )

        }

        Column(
            Modifier
                .fillMaxSize()
                .background(Color(240, 240, 240))
                .paint(
                    // Replace with your image id
                    painterResource(id = R.drawable.duck_logo),
                    contentScale = ContentScale.None)
        ) {

                Text(
                    text = "Login",
                    Modifier.padding(30.dp, 10.dp, 8.dp, 0.dp),
                    fontSize = 30.sp,
                    color = Color(150, 150, 150),
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Default,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(15.dp))

                TextField(
                    modifier = Modifier.padding(30.dp, 0.dp, 8.dp, 0.dp),
                    value = email,
                    onValueChange = {
                        email = it
                        emailError =
                            if (!Patterns.EMAIL_ADDRESS.matcher(it)
                                    .matches()
                            ) "Invalid email!" else ""
                        // This checks to make sure the email follows the email format.
                    },
                    label = { Text("email") },
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
                    modifier = Modifier.padding(30.dp, 0.dp, 8.dp, 0.dp),
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
                    label = { Text("password") },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                    // arranges the keyboard for password entry.
                )
                if (passwordError.isNotEmpty()) Text(
                    text = passwordError,
                    color = MaterialTheme.colorScheme.error
                )
                // Display an error message if the password field is empty.


                Button(
                    onClick = {
                        val valid = emailError.isEmpty() && passwordError.isEmpty()

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
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(74, 170, 255)),
                    modifier = Modifier.width(300.dp)
                        .padding(30.dp, 0.dp, 8.dp, 0.dp)
                ) {
                    Text("Login")
                }
            }
        }
    }


@Preview
@Composable
fun Display() {
    LoginScreen(context)
}
