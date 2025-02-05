package com.example.ktor.core.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ktor.ui.theme.Dark_blue_gray
import com.example.ktor.ui.theme.soft_blue_gray
import com.example.ktor.util.presentation.TextWea

@Composable
fun NotifcationBottomShee(isItNow: Boolean, time: String, discription: String, icon: Int ) {

    val color = if (isItNow) Dark_blue_gray else soft_blue_gray
    val backgroundColor = if (isItNow) Color(0xFF95E5FF).copy(0.29f) else Color.Unspecified
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .padding(horizontal = 16.dp)
            .height(96.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            tint = color
        )
        Spacer(modifier = Modifier.width(30.dp))
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
        ) {
            TextWea(
                time,
                fontSize = 12,
                textColor = color
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextWea(
                discription,
                fontSize = 14,
                fontWeight = FontWeight.Bold,
                textColor = color
            )
        }
        IconButton(
            modifier = Modifier.align(Alignment.CenterVertically),
            onClick = { /*TODO*/ }

        ) {
            Icon(
                Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                tint = color
            )
        }
    }
}