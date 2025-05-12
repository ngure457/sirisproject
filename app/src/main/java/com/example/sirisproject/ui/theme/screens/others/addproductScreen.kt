package com.example.sirisproject.ui.theme.screens.others

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.sirisproject.R
import coil.compose.AsyncImage
import com.example.sirisproject.data.ProductViewModel
import kotlin.let

@Composable
fun AddproductScreen(navController: NavController){
    val context = LocalContext.current
    val imageUri = rememberSaveable() { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent())
    { uri: Uri? -> uri?.let { imageUri.value=it } }
    var productname by rememberSaveable { mutableStateOf("") }
    var productquantity by rememberSaveable { mutableStateOf("") }
    var priceperproduct by rememberSaveable { mutableStateOf("") }
    var productbrand by rememberSaveable { mutableStateOf("") }
    var desc by rememberSaveable { mutableStateOf("") }
    val productViewModel: ProductViewModel = viewModel()

    Column (modifier = Modifier.fillMaxHeight().fillMaxWidth(), verticalArrangement = Arrangement.Center) {
        Text(
            text = "ADD NEW PRODUCT",
            fontSize = 40.sp,
            color = Color.Black,
            fontFamily = FontFamily.SansSerif,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .background(Color.Gray)
                .padding(10.dp)
                .fillMaxWidth()

        )
        Column(
            modifier = Modifier.fillMaxSize().padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                shape = CircleShape,
                modifier = Modifier.padding(10.dp).size(200.dp)
            ) {
                AsyncImage(
                    model = imageUri.value ?: R.drawable.ic_person,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(200.dp)
                        .clickable { launcher.launch("image/*") })

            }
            Text(
                text = "Attach product image",
                modifier = Modifier.padding(10.dp).align(alignment = Alignment.CenterHorizontally),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleLarge
            )

            OutlinedTextField(
                value = productname,
                onValueChange = { newProductname -> productname = newProductname },
                label = { Text(text = "Product name") },
                placeholder = { Text(text = "Enter product name") },
                modifier = Modifier.padding(10.dp).fillMaxWidth(),
            )
            OutlinedTextField(
                value = productquantity,
                onValueChange = { newProductquantity -> productquantity = newProductquantity },
                label = { Text(text = " The Product quantity") },
                placeholder = { Text(text = "Enter product's quantity") },
                modifier = Modifier.padding(10.dp).fillMaxWidth(),
            )
            OutlinedTextField(
                value = priceperproduct,
                onValueChange = { newPriceperproduct -> priceperproduct = newPriceperproduct },
                label = { Text(text = "Price per product") },
                placeholder = { Text(text = "Enter price per product") },
                modifier = Modifier.padding(10.dp).fillMaxWidth(),
            )
            OutlinedTextField(
                value = productbrand,
                onValueChange = { newProductbrand -> productbrand = newProductbrand },
                label = { Text(text = "Product brand") },
                placeholder = { Text(text = "Enter product brand") },
                modifier = Modifier.padding(10.dp).fillMaxWidth(),
            )
            OutlinedTextField(
                value = desc,
                onValueChange = { newDesc -> desc = newDesc },
                label = { Text(text = "Description") },
                placeholder = { Text(text = "Enter description") },
                modifier = Modifier.padding(10.dp)
                    .height(150.dp)
                    .fillMaxWidth(),
                singleLine = false
            )
            Row(
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {},
                    modifier = Modifier.wrapContentWidth(),
                    colors = ButtonDefaults.buttonColors(Color.Green)
                ) {
                    Text(text = "HOME")
                }
                Button(
                    onClick = {
                        imageUri.value?.let {
                            productViewModel.uploadProductWithImage(
                                it, context,
                                productname,
                                productquantity,
                                priceperproduct,
                                productbrand,
                                desc,
                                navController)
                        } ?: Toast.makeText(context, "Please select an image", Toast.LENGTH_SHORT).show ()
                    },
                    modifier = Modifier.wrapContentWidth(),
                    colors = ButtonDefaults.buttonColors(Color.Black)
                ) { Text(text = "SAVE") }
            }


        }

    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddproductScreenPreview(){
    AddproductScreen(rememberNavController())
}