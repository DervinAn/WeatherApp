package com.example.ktor.core.presentation

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

@Composable
fun RequestLocationPermission(
    onLocationFetched: (Double, Double) -> Unit, // Callback to handle fetched location
    onPermissionDenied: () -> Unit // Callback to handle denied permissions
) {
    val context = LocalContext.current

    // Define the location permissions
    val locationPermissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    // Check if permissions are already granted
    val allPermissionsGranted = locationPermissions.all {
        ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
    }

    // Launcher for requesting permissions
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissionsMap ->
        val allGranted = permissionsMap.values.all { it }
        if (allGranted) {
            // Permissions granted, fetch location
            fetchLocation(context, onLocationFetched)
        } else {
            // Handle denied permissions
            onPermissionDenied()
        }
    }

    // Request permissions if not already granted
    LaunchedEffect(Unit) {
        if (!allPermissionsGranted) {
            permissionLauncher.launch(locationPermissions)
        } else {
            // Permissions already granted, fetch location
            fetchLocation(context, onLocationFetched)
        }
    }
}

// Function to fetch location
private fun fetchLocation(context: Context, onLocationFetched: (Double, Double) -> Unit) {
    val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    if (ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    ) {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                if (location != null) {
                    val latitude = location.latitude
                    val longitude = location.longitude
                    // Call the callback with the fetched location
                    onLocationFetched(latitude, longitude)
                } else {
                    // Handle the case where location is null
                }
            }
            .addOnFailureListener { exception ->
                // Handle failure
            }
    }
}