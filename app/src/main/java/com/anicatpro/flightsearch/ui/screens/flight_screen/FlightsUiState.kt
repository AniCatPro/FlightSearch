package com.anicatpro.flightsearch.ui.screens.flight_screen

import com.anicatpro.flightsearch.model.Airport
import com.anicatpro.flightsearch.model.Favorite

data class FlightsUiState(
    val code: String = "",
    val favoriteList: List<Favorite> = emptyList(),
    val destinationList: List<Airport> = emptyList(),
    val departureAirport: Airport = Airport(),
)
