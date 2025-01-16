package com.example.infotainment_trip.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.infotainment_trip.model.TripData
import com.example.infotainment_trip.model.TripDataItem
import dagger.hilt.android.lifecycle.HiltViewModel

class MainViewModel : ViewModel() {
    var datesNotEmpty by mutableStateOf(false)
    var dataFirstTime by mutableStateOf(true)
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
    var startAddress by mutableStateOf("")
    var endAddress by mutableStateOf("")

    var backgroundGradient = Brush.linearGradient(
        listOf(
            Color(0xFF040F36),
            Color(0xFF030A29)
        )
    )
    var mainBackGroundGradient = Brush.linearGradient(
        listOf(
            Color(0xFF040A2F),
            Color(0xFF060817)
        )
    )

    var buttonStroke = Brush.linearGradient(
        listOf(
            Color(0xFFFFFFFF).copy(alpha = 1f),
            Color(0xFFFFFFFF).copy(alpha = 1f),
            Color(0xFFFFFFFF).copy(alpha = 1f),
            Color(0xFFFFFFFF).copy(alpha = 1f)
        )
    )


    val detailsButtonBackGroundGradient = Brush.verticalGradient(
        listOf(
            Color(0xFF255AF5).copy(alpha = 1f),
            Color(0xFF255AF5).copy(alpha = 1f),
            Color(0xFF255AF5).copy(alpha = 1f),
            Color(0xFF090F26).copy(alpha = 1f)
        )
    )

    val tripBackGroundGradient = Brush.verticalGradient(
        listOf(
            Color(0xFF000000).copy(alpha = 0f),
            Color(0xFF76ADFF).copy(alpha = 0.2f)
        )
    )


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