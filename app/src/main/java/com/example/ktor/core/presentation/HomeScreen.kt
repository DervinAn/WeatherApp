package com.example.ktor.core.presentation

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ktor.R
import com.example.ktor.core.data.remote.KtorApiService
import com.example.ktor.core.data.repository.RepositoryImpl
import com.example.ktor.core.data.repository.RepositoryImplKtor
import com.example.ktor.core.data.toWeatherUi
import com.example.ktor.core.domain.repository.FieldValidator
import com.example.ktor.core.domain.repository.usecase.GetWeatherUseCase
import com.example.ktor.core.presentation.component.BottomSheetContent
import com.example.ktor.core.presentation.component.DetailsOfWeatherInCard
import com.example.ktor.core.presentation.component.ForecastButton
import com.example.ktor.core.presentation.component.TopAppBar
import com.example.ktor.ui.theme.gradientBlue
import com.example.ktor.util.local.DatabaseProvider
import com.example.ktor.util.network.KtorClient
import com.example.ktor.util.network.RetrofitClient
import com.example.ktor.util.presentation.ErrorMessage
import com.example.ktor.util.presentation.LoadingIndicator

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {

    val context = LocalContext.current
    val weatherDatabase = remember { DatabaseProvider.getDatabase(context) }
    val weatherDao = weatherDatabase.weatherDao()

    val repositoryImpl = RepositoryImpl(
        apiService = RetrofitClient.apiService,
        weatherDao = weatherDao
    )
    val repositoryImplKtor = RepositoryImplKtor(KtorApiService(KtorClient), weatherDao)
    val getWeatherUseCase = GetWeatherUseCase(repositoryImpl)
    val viewModel = remember { HomeScreenViewModel(getWeatherUseCase, FieldValidator()) }
    val city = viewModel.city.collectAsState().value
    Log.d("city", city)
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    var showTopSheet by remember { mutableStateOf(false) }



    // Handle location permission and fetch weather by location
    RequestLocationPermission(
        onLocationFetched = { latitude, longitude ->
            viewModel.fetchWeatherByLocation(latitude, longitude)
            Log.d("Location", "Latitude: $latitude, Longitude: $longitude")
        },
        onPermissionDenied = {
            // Handle denied permissions (e.g., show a message or guide to settings)
        }
    )

    // Collect UI state from ViewModel
    val state = viewModel.state.collectAsState()
    val allWeather = viewModel.allWeather.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .safeContentPadding()
            .background(gradientBlue),
    ) {
        // Top App Bar
        TopAppBar(
            modifier = Modifier.align(Alignment.TopCenter),
            onClickListener = { showBottomSheet = true },
            onClickListener2 = { showTopSheet = true },
            text = city
        )

        // Background Images
        Image(
            painter = painterResource(R.drawable.vector_wea),
            contentDescription = null,
            modifier = Modifier.align(Alignment.TopEnd)
        )
        Image(
            painter = painterResource(R.drawable.vector_wea2),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 80.dp)
        )


        // Weather Image
        val result1 = state.value
//        val result2 = if(result1 is UiState.Success) result1.data.toWeatherUi().icon else R.drawable.baseline_wifi_off_24
//        val result3 = if(result1 is UiState.Success) result1.data.toWeatherUi().isItNight else false
        Image(
            painter = painterResource(id = if(result1 is UiState.Success) result1.data.toWeatherUi().icon else R.drawable.weather_icon),
            contentDescription = stringResource(R.string.Weather_image),
            modifier = Modifier
//                .size(40.dp)
                .align(Alignment.TopCenter)
                .padding(top = 140.dp)
        )

        // Display Weather Data
        when (val result = state.value) {
            is UiState.Loading -> LoadingIndicator(modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 120.dp)
            )
            is UiState.Success -> {
                val weatherUi = result.data.toWeatherUi()
                DetailsOfWeatherInCard(
                    weatherUi = weatherUi,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(top = 120.dp)
                )
            }
            is UiState.Error ->
            ErrorMessage(result.message
                ,modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 120.dp)
            )
        }

        // Forecast Button
        ForecastButton(
            modifier = Modifier.align(Alignment.BottomCenter),
            onClick = {
            Toast.makeText(context, "This feature will be added soon", Toast.LENGTH_SHORT).show()
        })

        // Top Sheet (Weather Search)
        AnimatedVisibility(
            visible = showTopSheet,
            enter = slideInVertically(initialOffsetY = { -it }),
            exit = slideOutVertically(targetOffsetY = { -it }),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f)
        ) {
            WeatherSearchApp(
                onBack = { showTopSheet = false },
                onSearch = { newCity ->
                    viewModel.checkField(newCity)
                    if (viewModel.state.value is UiState.Success) {
                        viewModel.updateCity(newCity)
                        showTopSheet = false
                    }
                },
                allWeather = allWeather.value
            ){ city->
                /** is using launchEffect her better **/
                viewModel.updateCity(city.toString())
                showTopSheet = false
                Log.d("city", city.toString())
            }
        }

        // Bottom Sheet (Notifications)
        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false },
                sheetState = sheetState,
            ) {
                BottomSheetContent()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
//    HomeScreen()
}