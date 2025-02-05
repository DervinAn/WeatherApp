package com.example.ktor.core.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ktor.R
import com.example.ktor.util.presentation.TextWea

@Composable
fun TopAppBar(modifier: Modifier = Modifier,onClickListener: () -> Unit,onClickListener2: () -> Unit, text: String) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 31.dp, vertical = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(
                onClick = {
                    onClickListener2()
                },
            ) {
                Icon(
                    painter = painterResource(R.drawable.loaction_wea),
                    contentDescription = "Position",
                    tint = Color.White,
                    modifier = Modifier.size(30.dp)
                )
            }

            Spacer(modifier = Modifier.width(8.dp))
            TextButton(
                onClick = {
                    onClickListener2()
                },
                modifier = Modifier.padding(start = 8.dp)
            ) {
                TextWea(
                    text = text,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20
                )
            }
        }
        IconButton(
            onClick = { onClickListener() },
        ) {
            Icon(
                painter = painterResource(R.drawable.notifaction_wea),
                contentDescription = "notification",
                tint = Color.White
            )
        }

    }
}