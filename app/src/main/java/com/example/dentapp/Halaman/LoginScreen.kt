package com.example.dentapp.Halaman

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dentapp.Util.Screen
import com.example.dentapp.ui.YesButton
import com.example.dentapp.ui.gradbg

@Composable
fun LoginScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .background(gradbg)
            .fillMaxSize()
            .padding(20.dp)
    ){
        Column {
            TitleLogin()
            LoginForm(navController)
        }
    }
}

@Composable
fun TitleLogin() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp, start = 30.dp, end = 30.dp, bottom = 50.dp)
    ) {
        Text(
            text = "Halaman Login",
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(start = 10.dp,top = 10.dp,bottom = 30.dp)
        )
    }
}


@Composable
fun LoginForm(navController: NavController) {
    val context = LocalContext.current
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = 15.dp,vertical = 20.dp)

    ) {
        EmailTextField()
        PassTextField()
        Button(
            modifier = Modifier
                .background(YesButton)
                .fillMaxWidth(),
            onClick = {
                Toast.makeText(context, "Login Berhasil", Toast.LENGTH_SHORT).show()//diubah buat login
                navController.navigate(Screen.WelcomeScreen.route){popUpTo(0)}
            }
        ) {
            Text(
                text = "Login",
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.padding(5.dp)
            )
        }
        Text(
            text = "Belum pernah mendaftar? Sign Up here",
            modifier = Modifier
                .clickable {
                    navController.navigate(Screen.SignUpScreen.route)
                }
                .padding(10.dp),
            textAlign = TextAlign.Center,
            color = Color.Blue
        )
    }
}
@Composable
fun EmailTextField() {
    var texts by remember { mutableStateOf("") }
    val invalidInput = texts.count() > 5 && '@' !in texts
    Box(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = texts,
                onValueChange = { texts = it},
                placeholder = {
                    Text(text = "Input Email")
                },
                label = {
                    val label = labelEmail(texts,invalidInput)
                    Text(label)
                },
                leadingIcon = { Icon(imageVector = Icons.Filled.Email, contentDescription = "iconEmail" )},
            )
            val textColor = if (texts == "") {
                if (invalidInput) {
                    MaterialTheme.colors.error
                } else MaterialTheme.colors.onSurface
            }else{
                MaterialTheme.colors.onSurface
            }
            Text(
                text = if (invalidInput) "Requires '@' in email" else "Helper message",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.caption.copy(color = textColor)
            )

        }
    }
}

fun labelEmail(texts:String ,invalidInput:Boolean): String {
    var labelEmail = ""
    if (texts == ""){
        labelEmail = "Email"
    }else{
        if (invalidInput){
            labelEmail = "Email*"
        }else{
            labelEmail="Email"
        }
    }
    return labelEmail
}
fun labelPass(texts:String ,invalidInput:Boolean): String {
    var labelPass = ""
    if (texts == "") {
        labelPass = "Password"
    } else {
        if (invalidInput) {
            labelPass = "Password*"
        } else {
            labelPass = "Password"
        }
    }
    return labelPass
}
@Composable
fun PassTextField() {
    var pass by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    val invalidInput = pass.count() < 8
    Box(
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 10.dp)
            .fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = pass,
                onValueChange = { pass = it},
                placeholder = {
                    Text(text = "Input Password")
                },
                label = {
                    val label = labelPass(pass,invalidInput)
                    Text(text = label)
                },
                visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                leadingIcon = { Icon(imageVector = Icons.Filled.Lock, contentDescription = "iconLock" )},
                trailingIcon = {
                    val image = if (passwordVisibility)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff

                    IconButton(onClick = {
                        passwordVisibility = !passwordVisibility
                    }) {
                        Icon(imageVector  = image, "")
                    }
                }

            )
            Text(
                text = if (invalidInput) "Password lenght must over 8 " else "Helper message",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.caption.copy(color = MaterialTheme.colors.onSurface)
            )

        }
    }
}