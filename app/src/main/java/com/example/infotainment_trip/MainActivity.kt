package com.example.infotainment_trip

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import com.example.infotainment_trip.components.MainScreen
import com.example.infotainment_trip.ui.theme.InfotainmentTripTheme
import com.example.infotainment_trip.viewModel.MainViewModel
import com.mappls.sdk.maps.MapplsMap
import com.mappls.sdk.maps.OnMapReadyCallback
import com.mappls.sdk.maps.annotations.MarkerOptions
import com.mappls.sdk.maps.annotations.PolylineOptions
import com.mappls.sdk.maps.camera.CameraPosition
import com.mappls.sdk.maps.camera.CameraUpdateFactory
import com.mappls.sdk.maps.geometry.LatLng
import com.mappls.sdk.maps.geometry.LatLngBounds

class MainActivity : ComponentActivity(), OnMapReadyCallback {
    val viewModel: MainViewModel by viewModels<MainViewModel>()
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InfotainmentTripTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val context = LocalContext.current
                    val sharedPreferences = context.getSharedPreferences("DeviceId", MODE_PRIVATE)
                    val deviceID = sharedPreferences.edit()
                    deviceID.putInt("DeviceID", 4)
                    deviceID.apply()

                    val deviceId = sharedPreferences.getInt("DeviceID", 0)
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



            val listOfLatLng = mutableStateListOf<LatLng>()

            for (i in 0 until viewModel.tripData.trailData.size) {
                val lat = viewModel.tripData.trailData[i].split(",").get(0).toDouble()
                val long = viewModel.tripData.trailData[i].split(",").get(1).toDouble()
                listOfLatLng.add(LatLng(lat,long))
            }

            val latLngBounds = LatLngBounds.Builder().includes(listOfLatLng).build()
            mapplsMap.animateCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, 30))

            mapplsMap.addPolyline(
                PolylineOptions()
                .addAll(listOfLatLng)
                .color(Color.Red.toArgb())
                .width(3f))

            viewModel.changeLocation = true
        }

//        val cameraPosition = CameraPosition.Builder().target(
//            LatLng(
//                viewModel.startLocationLat, viewModel.startLocationLong
//            )
//        ).zoom(15.0).tilt(0.0).build()
//        mapplsMap.cameraPosition = cameraPosition
    }

    override fun onMapError(mapplsMap: Int, p1: String?) {

    }
}
