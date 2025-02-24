package com.anicatpro.flightsearch.ui.screens.flight_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anicatpro.flightsearch.data.MockData
import com.anicatpro.flightsearch.ui.screens.search.AirportRow

@Composable
fun FlightRow(
    modifier: Modifier = Modifier,
    isFavorite: Boolean,
    departureAirportCode: String,
    departureAirportName: String,
    destinationAirportCode: String,
    destinationAirportName: String,
    onFavoriteClick: (String, String) -> Unit,
) {
    Card(
        elevation = 12.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(7.dp)
    ) {
        Row {
            Column(
                modifier = modifier.weight(20f)
            ) {
                Column {
                    Text(
                        text = "Отправление",
                        style = MaterialTheme.typography.overline,
                        color = Color.Gray,
                        modifier = Modifier.padding(start = 20.dp)
                    )
                    AirportRow(code = departureAirportCode, name = departureAirportName, textColor = androidx.compose.ui.graphics.Color.Blue)

                    Divider(
                        color = Color.LightGray,
                        thickness = 1.dp,
                        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
                    )

                    Text(
                        text = "Прибытие",
                        style = MaterialTheme.typography.overline,
                        color = Color.Gray,
                        modifier = Modifier.padding(start = 20.dp)
                    )
                    AirportRow(code = destinationAirportCode, name = destinationAirportName, textColor = androidx.compose.ui.graphics.Color.Blue)
                }
            }
            IconButton(
                onClick = {
                    onFavoriteClick(departureAirportCode, destinationAirportCode)
                },
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Icon(
                    imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    tint = if (isFavorite) Color.Blue else Color.LightGray,
                    contentDescription = null
                )
            }
        }
    }
}


@Preview
@Composable
fun FlightRowPreview() {
    val mock = MockData
    FlightRow(
        isFavorite = true,
        departureAirportCode = mock.airports[1].code,
        departureAirportName = mock.airports[1].name,
        destinationAirportCode = mock.airports[2].code,
        destinationAirportName = mock.airports[2].name,
        onFavoriteClick = { _: String, _:String ->}
    )
}

