package com.anicatpro.flightsearch.ui.screens.search

import com.anicatpro.flightsearch.model.Airport
import com.anicatpro.flightsearch.model.Favorite

data class SearchUiState(
    val searchQuery: String = "",
    val selectedCode: String = "",
    val airportList: List<Airport> = emptyList(),
    val favoriteList: List<Favorite> = emptyList(),
)