package com.marko.a330_cs.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marko.a330_cs.R
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import android.Manifest


@Composable
fun RashidScreen() {
    val context = LocalContext.current
    var photoUri by remember { mutableStateOf<Uri?>(null) }
    var hasCameraPermission by remember { mutableStateOf(false) }

    // Function to create a photo URI using MediaStore
    val createImageUri = {
        val contentValues = android.content.ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "photo_${System.currentTimeMillis()}.jpg")
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
        }
        context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)?.also { uri ->
            photoUri = uri
        }
    }

    // Launcher to handle the result of the camera intent
    val cameraLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { success ->
        if (success) {
            // Handle the photoUri here (e.g., display the photo or save it)
        }
    }

    // Launcher to request camera permission
    val permissionLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        hasCameraPermission = isGranted
        if (isGranted) {
            createImageUri()
            photoUri?.let { uri ->
                cameraLauncher.launch(uri)
            }
        } else {
            // Handle the case where the permission is not granted
            Toast.makeText(context, "Camera permission is required to take photos", Toast.LENGTH_SHORT).show()
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.r_logo),
                contentDescription = "logo",
                modifier = Modifier
                    .graphicsLayer(
                        scaleX = 0.75f,
                        scaleY = 0.75f,
                        alpha = 1f
                    )
            )
            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = {
                permissionLauncher.launch(android.Manifest.permission.CAMERA)
            }) {
                Text(text = "Open Camera", fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { /* TODO: Add action for Button 2 */ }) {
                Text(text = "Button 2", fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { /* TODO: Add action for Button 3 */ }) {
                Text(text = "Button 3", fontSize = 20.sp)
            }
        }
    }
}
