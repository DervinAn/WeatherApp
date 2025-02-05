package com.example.ktor.core.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ktor.R
import com.example.ktor.ui.theme.Dark_blue_gray
import com.example.ktor.ui.theme.soft_blue_gray
import com.example.ktor.util.presentation.TextWea

@Composable
fun BottomSheetContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        TextWea(
            text = "Your notification",
            fontSize = 24,
            textColor = Dark_blue_gray,
            fontWeight = FontWeight.Black,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextWea(
            "New", fontSize = 12, textColor = Dark_blue_gray,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        NotifcationBottomShee(
            true, "10 min ago", "A sunny day in your location, consider " +
                    "wearing your UV protection", R.drawable.icon_of_sun
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextWea(
            "Earlier", fontSize = 12, textColor = soft_blue_gray,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        NotifcationBottomShee(
            false,
            "1 day ago", "A cloudy day will occur all day long," +
                    " don't worry about the heat of the sun", R.drawable.wind_icon
        )
        NotifcationBottomShee(
            false,
            "2 days ago", "Potential for rain today is 84%, don't " +
                    "forget to bring your umbrella.", R.drawable.potential_for_rain
        )

    }

}