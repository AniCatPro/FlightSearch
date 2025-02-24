package com.anicatpro.flightsearch.ui.screens.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun AirportRow(
    modifier: Modifier = Modifier,
    code: String,
    name: String,
    onSelectCode: (String) -> Unit = { },
    textColor: androidx.compose.ui.graphics.Color = androidx.compose.ui.graphics.Color.Black
) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .clickable(
                    onClick = {
                        if (code != "") {
                            onSelectCode(code)
                        }
                    },
                )
        ) {
                Spacer(
                    modifier = Modifier.width(24.dp)
                )
            Text(
                text = code,
                fontWeight = FontWeight.Bold,
                color = textColor
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
    }
}


