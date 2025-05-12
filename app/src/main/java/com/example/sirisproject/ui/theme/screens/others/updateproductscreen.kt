package com.example.sirisproject.ui.theme.screens.others

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.sirisproject.R
import com.example.sirisproject.data.ProductViewModel
import com.example.sirisproject.models.ProductModel

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


@Composable
fun UpdateproductScreen(navController: NavController,productId: String){
    val context = LocalContext.current
    val imageUri = rememberSaveable() { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent())
    {uri: Uri? -> uri?.let{imageUri.value=it} }
    var productname by remember { mutableStateOf("") }
    var productquantity by remember { mutableStateOf("") }
    var productprice by remember { mutableStateOf("") }
    var desc by remember { mutableStateOf("") }

    val currentDataRef  = FirebaseDatabase.getInstance()
        .getReference().child("Products/$productId")
    DisposableEffect(Unit){
        val listener = object  : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val product = snapshot.getValue(ProductModel::class.java)
                product?.let {
                    productname = it.productname
                    productquantity = it.productquantity
                    productprice = it.productprice
                    desc = it.desc
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context,error.message,Toast.LENGTH_LONG).show()
            }
        }
        currentDataRef.addValueEventListener(listener)
        onDispose { currentDataRef.removeEventListener(listener) }
    }
    Column(modifier = Modifier.fillMaxSize().padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Card (shape = CircleShape,
            modifier = Modifier.padding(10.dp).size(200.dp)){
            AsyncImage(model = imageUri.value ?: R.drawable.ic_person,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(200.dp)
                    .clickable { launcher.launch("image/*") })
        }
        Text(text = "Attach product image")

        OutlinedTextField(value = productname,
            onValueChange = {newProductname ->productname=newProductname},
            label = { Text(text = "Product Name") },
            placeholder = { Text(text = "Please enter product name") },
            modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = productquantity,
            onValueChange = {newProductquantity ->productquantity=newProductquantity},
            label = { Text(text = "Product Quantity") },
            placeholder = { Text(text = "Please enter product quantity") },
            modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = productprice,
            onValueChange = {newProductprice ->productprice=newProductprice},
            label = { Text(text = "Unit Product Price") },
            placeholder = { Text(text = "Please enter unit product price") },
            modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = desc,
            onValueChange ={newDescription->desc=newDescription},
            label = { Text(text = "Brief description") },
            placeholder = { Text(text = "Please enter product description") },
            modifier = Modifier.fillMaxWidth().height(150.dp),
            singleLine = false)
        Row (modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween){
            Button(onClick = {}) { Text(text = "All Products") }
            Button(onClick = {
                val productRepository = ProductViewModel()
                productRepository.updateProduct(
                    context = context,
                    navController = navController,
                    productname = productname,
                    productquantity = productquantity,
                    productprice = productprice,
                    desc = desc,
                    productId = productId
                )
            }) { Text(text = "UPDATE") }
        }
    }
}