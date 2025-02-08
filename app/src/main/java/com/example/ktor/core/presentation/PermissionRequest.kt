package com.example.ktor.core.presentation

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
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
    onPermissionDenied: () -> Unit, // Callback to handle denied permissions
    onLocationFetchFailed: () -> Unit = {} // Optional callback for location fetch failure
) {
    val context = LocalContext.current

    // Define the location permissions
    val locationPermissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)

    // Check if permissions are already granted
    val isPermissionGranted = ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED

    // Launcher for requesting permissions
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissionsMap ->
        val allGranted = permissionsMap.values.all { it }
        if (allGranted) {
            // Permissions granted, fetch location
            fetchLocation(context, onLocationFetched, onLocationFetchFailed)
        } else {
            // Handle denied permissions
            onPermissionDenied()
        }
    }

    // Request permissions if not already granted
    LaunchedEffect(Unit) {
        if (!isPermissionGranted) {
            permissionLauncher.launch(locationPermissions)
        } else {
            // Permissions already granted, fetch location
            fetchLocation(context, onLocationFetched, onLocationFetchFailed)
        }
    }
}

// Function to fetch location
private fun fetchLocation(
    context: Context,
    onLocationFetched: (Double, Double) -> Unit,
    onLocationFetchFailed: () -> Unit
) {
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
                    Log.d("Fetched Location_Permission","Fetched Location: Latitude=$latitude, Longitude=$longitude")
                    onLocationFetched(latitude, longitude)
                } else {
                    Log.w("Failed to fetch location","Location is null. Unable to fetch current location.")
                    onLocationFetchFailed()
                }
            }
            .addOnFailureListener {
                Log.d("Exception_Permission", "Failed to fetch location")
                onLocationFetchFailed()
            }
    } else {
        Log.e("Permission Denied","Location permission not granted. Unable to fetch location.")
        onLocationFetchFailed()
    }
}