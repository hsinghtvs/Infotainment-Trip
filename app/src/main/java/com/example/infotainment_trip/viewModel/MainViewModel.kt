package com.example.infotainment_trip.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.infotainment_trip.model.TripData
import com.example.infotainment_trip.model.TripDataItem
import dagger.hilt.android.lifecycle.HiltViewModel

class MainViewModel : ViewModel() {
    var datesNotEmpty by mutableStateOf(true)
    var fromDate by mutableStateOf("From")
    var toDate by mutableStateOf("To")
    var tripSummary by mutableStateOf(TripData())
    var tripIndexSelected by mutableStateOf(0)
    var dataLoading by mutableStateOf(false)
    var dataError by mutableStateOf("")
    var changeLocation by mutableStateOf(false)
    var startLocationLat by mutableStateOf(0.0)
    var endLocationLat by mutableStateOf(0.0)
    var startLocationLong by mutableStateOf(0.0)
    var endLocationLong by mutableStateOf(0.0)
    var tripData by mutableStateOf(
        TripDataItem(
            "2022-01-01",
            "2022-01-01",
            "2022-01-01",
            "2022-01-01",
            "7",
            "7",
            "7",
            "2022-01-01",
            "2022-01-01",
            "2022-01-01",
            "2022-01-01",
            "2022-01-01",
            "2022-01-01",
            "2022-01-01",
            "2022-01-01",
            "2022-01-01",
            7,
            7,
            7.0,
            "7",
            "2022-01-01",
            "2022-01-01",
            "2022-01-01",
            "7",
            "2022-01-01",
            0.0,
            emptyList(),
            "",
            ""

        )
    )
}