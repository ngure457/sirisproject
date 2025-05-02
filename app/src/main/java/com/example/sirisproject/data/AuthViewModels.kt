package com.example.sirisproject.data

import android.content.Context
import android.widget.Toast
import kotlinx.coroutines.flow.MutableStateFlow

//data class AuthViewModels(){
    //private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
//    private val _isLoading = MutableStateFlow(false)
//    private val _errorMessage = MutableStateFlow<String?>(null)
//
//
//    fun signup(
//        firstname: String, lastname: String, email: String, password: String,
//        navController: NavController,
//        context: Context
//    ) {
//        if (firstname.isBlank() || lastname.isBlank() || email.isBlank() || password.isBlank()) {
//            Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_LONG).show()
//
//            return
//        }
//        _isLoading.value = true
//
//        mAuth.createUserWithEmailAndPassword(email, password)
//            .addOnCompleteListener { task ->
//                _isLoading.value = false
//                if (task.isSuccessful) {
//                    val userId = mAuth.currentUser?.uid ?: ""
//                    val userData = UserModel(
//                        firstname = firstname,
//                        lastname = lastname,
//                        email = email,
//                        password = password,
//                        userId = userId
//                    )
//
//                    saveUserToDatabase(userId, userData, navController, context)
//
//                } else {
//                    _errorMessage.value = task.exception?.message
//
//                    Toast.makeText(context, "Registration failed", Toast.LENGTH_LONG).show()
//                }
//            }
//    }
//}
