package com.example.ktor.core.presentation.component

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ktor.R
import com.example.ktor.core.data.WeatherUi
import com.example.ktor.ui.theme.gradientGray
import com.example.ktor.util.presentation.TextWea
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

@SuppressLint("NewApi")
@Composable
fun DetailsOfWeatherInCard(weatherUi: WeatherUi, modifier: Modifier = Modifier) {



    val formattedDate = remember {
        val currentDate = LocalDate.now()
        val dayOfMonth = currentDate.format(DateTimeFormatter.ofPattern("d")) // 12
        val dayOfWeek = currentDate.format(DateTimeFormatter.ofPattern("EEEE")) // Monday

        "$dayOfMonth $dayOfWeek" // Combine them: "12 Monday"
    }
        Card(
            modifier = modifier
                .fillMaxWidth(0.8f)
                .fillMaxHeight(0.45f),
            shape = CardDefaults.shape,
            border = BorderStroke(2.dp, gradientGray),
            colors = CardDefaults.cardColors(
                containerColor = Color.White.copy(alpha = 0.3f)
            ),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                TextWea(
                    text = formattedDate,
                    fontSize = 18,
                    fontWeight = FontWeight.Medium,
                )
                TextWea(
                    text = "${((weatherUi.temperature - 273.15) * 10).roundToInt() / 10.0}°C", // Rounds to 1 decimal place
                    fontSize = 70,
                    fontWeight = FontWeight.Normal,
                    shadow = Offset(-45f, 75f),
                    blur = 90f
                )
                TextWea(
                    text = weatherUi.description
                )
                Spacer(modifier = Modifier.height(27.dp))
                WeatherDetailRow(
                    iconResId = R.drawable.wind_icon,
                    label = "Wind",
                    value = "${(weatherUi.windSpeed * 3.6).toInt()} km/h" // Convert m/s to km/h and round to an integer
                )
                Spacer(modifier = Modifier.height(20.dp))

                // Use the reusable function for the second Row
                WeatherDetailRow(
                    iconResId = R.drawable.humdity_icon,
                    label = "Hum",
                    value = "${weatherUi.humidity}%",
                )
            }
        }
}




@Composable
fun WeatherDetailRow(
    iconResId: Int,
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Icon(
                painter = painterResource(id = iconResId),
                contentDescription = null,
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(23.dp))
            TextWea(text = label,textAlign = TextAlign.End)
        }

        Spacer(modifier = Modifier.width(20.dp))
        TextWea(text = "|")
        Spacer(modifier = Modifier.width(20.dp))
        TextWea(text = value, modifier = Modifier.weight(1f), textAlign = TextAlign.Start)
    }
}

@Preview(showBackground = true)
@Composable
private fun CardPreview() {
    DetailsOfWeatherInCard(
        weatherUi = WeatherUi(
            main = "Sunny",
            description = "Clear sky",
            temperature = 25.0,
            humidity = 60,
            icon = R.drawable.glow_of_sun
        )
    )

}