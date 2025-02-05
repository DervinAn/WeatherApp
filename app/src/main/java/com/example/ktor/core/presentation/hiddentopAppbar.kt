package com.example.ktor.core.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ktor.R
import com.example.ktor.core.data.local.WeatherEntity
import com.example.ktor.util.presentation.TextWea


@Composable
fun WeatherSearchApp(
    onBack: () -> Unit,
    onSearch: (String) -> Unit,
    allWeather: List<WeatherEntity>,
    onClick: (Any) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .clip(shape = RoundedCornerShape(0.dp, 0.dp, 6.dp, 6.dp))
    ) {
        Column (
            modifier = Modifier.padding(16.dp)
        ){
            SearchBar(
                query = searchQuery,
                onQueryChange = { searchQuery = it },
                onSearch = { onSearch(searchQuery) },
                onBack = {
                    onBack()
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text="Recent search", fontSize = 16.sp, color = Color(0xFF444E72), fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(10.dp))
            RecentSearchesList(recentSearches = allWeather){
                onClick(it)
            }
        }

    }
}

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
    onBack: () -> Unit
) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = Modifier.fillMaxWidth().clip(shape = RoundedCornerShape(0.dp, 0.dp, 6.dp, 6.dp)),
        placeholder = { Text("Search here") },
        trailingIcon = {
            IconButton(onClick = onSearch) {
                Icon(imageVector = Icons.Default.Search, contentDescription = null, tint = Color(0xFF444E72))
            }
        },
        leadingIcon ={
            IconButton(onClick = {
                onBack()
            }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null,tint = Color(0xFF444E72))
            }
        },
        shape = CircleShape,
        colors = TextFieldDefaults.colors(
            focusedPlaceholderColor = Color.Black,
            unfocusedPlaceholderColor = Color.Black,
            focusedIndicatorColor = Color(0xFF444E72),
            unfocusedIndicatorColor = Color.White,
            cursorColor = Color.White,
            focusedContainerColor = Color(0xB0636E96),
            unfocusedContainerColor = Color.White
            ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { onSearch() }),
        singleLine = true
    )
}

@Composable
fun RecentSearchesList(recentSearches: List<WeatherEntity>,onClick: (String) -> Unit) {
    LazyColumn {
        items(recentSearches) { search ->
            RecentSearchItem(search){
                onClick(search.city)
            }
        }
    }
}

@Composable
fun RecentSearchItem(search: WeatherEntity,onClick: () -> Unit ) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 13.dp)
            .clickable {
                onClick()
            },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ){
            Icon(painter = painterResource(R.drawable.clock),contentDescription = null, tint = Color(0xFF444E72))
            Text(text = search.city, fontSize = 18.sp, color = Color(0xFF444E72), fontWeight = FontWeight.Bold)
        }
        TextWea(text = "${search.temperature}°C", fontSize = 18, textColor = Color(0xFF444E72), fontWeight = FontWeight.Bold)
    }
}