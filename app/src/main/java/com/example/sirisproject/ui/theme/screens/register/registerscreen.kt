package com.example.sirisproject.ui.theme.screens.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sirisproject.R
import androidx.navigation.NavController
import com.example.sirisproject.navigation.ROUTE_LOGIN


@Composable
fun RegisterScreen(navController: NavController) {
   // val authViewModel : AuthViewModel = viewModel()
    var firstname by remember { mutableStateOf(value = "") }
    var lastname by remember { mutableStateOf(value = "") }
    var email by remember { mutableStateOf(value = "") }
    var password by remember { mutableStateOf(value = "") }
    var passwordVisibility by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column (modifier = Modifier.fillMaxHeight().fillMaxWidth(), verticalArrangement = Arrangement.Center) {
        Text(text = "Register Here !",
            fontSize = 30.sp,
            color = Color.Black,
            fontFamily = FontFamily.SansSerif,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center,
            modifier = Modifier
                    .background(Color.Gray)
                     .padding(10.dp)
                  .fillMaxWidth()
        )

        Image(painter = painterResource(R.drawable.logo),
            contentDescription = "logo",
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .height(150.dp))

        OutlinedTextField(value = firstname,
            onValueChange = {newFirstname -> firstname =newFirstname },
            label = { Text(text = "Enter your  firstname") },
            placeholder = { Text(text = "Please enter your firstname") },
            modifier = Modifier.wrapContentWidth().align(Alignment.CenterHorizontally),
            leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = "Person Icon") }
        )
        OutlinedTextField(value = lastname,
            onValueChange = {newLastname -> lastname =newLastname },
            label = { Text(text = "Enter your lastname") },
            placeholder = { Text(text = "Please enter your lastname") },
            modifier = Modifier.wrapContentWidth().align(Alignment.CenterHorizontally),
            leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription ="Person Icon") }
        )
        OutlinedTextField(value = email,
            onValueChange = { newEmail->email=newEmail},
            label = { Text(text = "Enter your email") },
            placeholder = { Text(text = "Please enter your email") },
            modifier = Modifier.wrapContentWidth().align(Alignment.CenterHorizontally),
            leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = "Email Icon") }
        )
        OutlinedTextField(value = password, onValueChange = {password= it},
            label = { Text(text = "Enter your password")},
            placeholder = { Text(text = "Please enter your password") },
            modifier = Modifier
                .height(100.dp)
                .align(Alignment.CenterHorizontally),
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Blue,
                unfocusedBorderColor = Color.Gray,
                cursorColor = Color.Blue,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
            ),
            leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "Password",
                tint = Color.Gray) },
            trailingIcon = {
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(
                        imageVector = if (passwordVisibility) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                        contentDescription = if (passwordVisibility)"Hide Password" else "Show Password",
                        tint = Color.Gray
                    )
                }
            },
            singleLine = true

        )


        Button(onClick = {
           // authViewModel.signup(firstname,lastname,email,password,navController,context)
        }
            ,modifier = Modifier.wrapContentWidth().align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(Color.Blue)) {
            Text(text = "Register")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = buildAnnotatedString{ append("If already registered,Login here") },
            modifier = Modifier.wrapContentWidth().
            align(Alignment.CenterHorizontally).
            clickable {
                navController.navigate(ROUTE_LOGIN)

            })

    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegisterScreenPreview(){
    com.example.sirisproject.ui.theme.SirisProjectTheme {
}
}



