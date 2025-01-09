package com.example.infotainment_trip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.example.infotainment_trip.components.MainScreen
import com.example.infotainment_trip.ui.theme.InfotainmentTripTheme
import com.example.infotainment_trip.viewModel.MainViewModel
import com.mappls.sdk.maps.MapplsMap
import com.mappls.sdk.maps.OnMapReadyCallback
import com.mappls.sdk.maps.annotations.MarkerOptions
import com.mappls.sdk.maps.camera.CameraPosition
import com.mappls.sdk.maps.geometry.LatLng

class MainActivity : ComponentActivity(), OnMapReadyCallback {
    val viewModel: MainViewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InfotainmentTripTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(viewModel = viewModel, mainActivity = this@MainActivity)
                }
            }
        }
    }

    override fun onMapReady(mapplsMap: MapplsMap) {

        if (viewModel.changeLocation) {
            val markers = mapplsMap.markers
            if (markers.size > 0) {
                for (i in 0 until markers.size) {
                    mapplsMap?.removeMarker(markers[i])
                }
            }
        }
        if (viewModel.startLocationLat != 0.0) {

            val latLongStart: LatLng =
                LatLng(viewModel.startLocationLat, viewModel.startLocationLong)
            val markerOptionsStart: MarkerOptions = MarkerOptions().position(latLongStart)
            mapplsMap?.addMarker(markerOptionsStart)

            val latLongEnd: LatLng = LatLng(viewModel.endLocationLat, viewModel.endLocationLong)
            val markerOptionsEnd: MarkerOptions = MarkerOptions().position(latLongEnd)
            mapplsMap?.addMarker(markerOptionsEnd)

            viewModel.changeLocation = true
        }

        val cameraPosition = CameraPosition.Builder().target(
            LatLng(
                viewModel.startLocationLat, viewModel.startLocationLong
            )
        ).zoom(15.0).tilt(0.0).build()
        mapplsMap.cameraPosition = cameraPosition
    }

    override fun onMapError(mapplsMap: Int, p1: String?) {

    }
}
