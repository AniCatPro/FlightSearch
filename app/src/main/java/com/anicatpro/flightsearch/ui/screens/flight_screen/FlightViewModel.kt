package com.anicatpro.flightsearch.ui.screens.flight_screen

import androidx.compose.runtime.*
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.anicatpro.flightsearch.FlightApplication
import com.anicatpro.flightsearch.data.FlightRepository
import com.anicatpro.flightsearch.model.Favorite
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class FlightViewModel(
    savedStateHandle: SavedStateHandle,
    val flightRepository: FlightRepository
): ViewModel()  {
    private val _uiState = MutableStateFlow(FlightsUiState())
    val uiState: StateFlow<FlightsUiState> = _uiState

    private val airportCode: String = savedStateHandle[FlightScreenDestination.codeArg] ?: ""

    var flightAdded: Boolean by mutableStateOf(false)

    init {
        viewModelScope.launch {
            processFlightList(airportCode)
        }
    }

    private fun processFlightList(airportCode: String) {

        viewModelScope.launch {
            val ff = flightRepository.getAllFavoritesFlights().toMutableStateList()
            val aa = flightRepository.getAllAirports().toMutableStateList()
            val departureAirport = aa.first { it.code == airportCode }
            _uiState.update {
                uiState.value.copy(
                    code = airportCode,
                    favoriteList = ff,
                    destinationList = aa,
                    departureAirport = departureAirport,
                )
            }
        }
    }

    fun addFavoriteFlight(departureCode: String, destinationCode: String) {
        viewModelScope.launch {
            val favorite: Favorite = flightRepository.getSingleFavorite(departureCode, destinationCode)

            if (favorite == null) {
                val tmp = Favorite(
                    departureCode = departureCode,
                    destinationCode = destinationCode,
                )
                flightAdded = true
                flightRepository.insertFavoriteFlight(tmp)
            } else {
                flightAdded = false
                flightRepository.deleteFavoriteFlight(favorite)
            }

            val play = flightRepository.getAllFavoritesFlights()
            _uiState.update {
                uiState.value.copy(
                    favoriteList = play,
                )
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FlightApplication)
                val flightRepository = application.container.flightRepository
                FlightViewModel(
                    this.createSavedStateHandle(),
                    flightRepository = flightRepository
                )
            }
        }
    }
}