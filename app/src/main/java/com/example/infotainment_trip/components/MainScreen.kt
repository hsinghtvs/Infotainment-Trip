package com.example.infotainment_trip.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.infotainment_rsa.model.AddressModel
import com.example.infotainment_trip.MainActivity
import com.example.infotainment_trip.R
import com.example.infotainment_trip.model.TripData
import com.example.infotainment_trip.viewModel.MainViewModel
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

@Composable
fun MainScreen(
    viewModel: MainViewModel,
    mainActivity: MainActivity
) {
    val backGroundGradient = Brush.verticalGradient(
        listOf(
            Color(0xFF040A1B).copy(alpha = 1f),
            Color(0xFF040A1B).copy(alpha = 1f),
            Color(0xFF040A1B).copy(alpha = 1f),
            Color(0xFF040A1B).copy(alpha = 1f),
            Color(0xFF040A1B).copy(alpha = 1f),
            Color(0xFF040A1B).copy(alpha = 1f),
            Color(0xFF040A1B).copy(alpha = 1f),
            Color(0xFF040A1B).copy(alpha = 1f),
            Color(0xFF040A1B).copy(alpha = 1f),
            Color(0xFF040A1B).copy(alpha = 1f)
        )
    )

    val buttonBackGroundGradient = Brush.verticalGradient(
        listOf(
            Color(0xFF2C2C2C).copy(alpha = 0.1f),
            Color(0xFF2C2C2C).copy(alpha = 0.1f)
        )
    )
    val buttonBackGroundGradientTwo = Brush.verticalGradient(
        listOf(
            Color(0xFFECFEEE).copy(alpha = 0.3f),
            Color(0xFFECFEEE).copy(alpha = 0.3f)
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = backGroundGradient)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Trip Details",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.manrope_extrabold)),
                    color = Color.White
                )
            )

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = viewModel.fromDate,
                    modifier = Modifier
                        .padding(10.dp)
                        .background(
                            brush = buttonBackGroundGradient,
                            shape = RoundedCornerShape(30.dp)
                        )
                        .background(
                            brush = buttonBackGroundGradientTwo,
                            shape = RoundedCornerShape(30.dp)
                        )
                        .padding(vertical = 5.dp)
                        .width(100.dp),
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.manrope_bold)),
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                )
                Spacer(modifier = Modifier.size(10.dp))
                Spacer(
                    modifier = Modifier
                        .width(10.dp)
                        .height(1.dp)
                        .background(color = Color.White)
                )
                Spacer(modifier = Modifier.size(10.dp))
                Text(
                    text = viewModel.toDate,
                    modifier = Modifier
                        .padding(10.dp)
                        .background(
                            brush = buttonBackGroundGradient,
                            shape = RoundedCornerShape(30.dp)
                        )
                        .background(
                            brush = buttonBackGroundGradientTwo,
                            shape = RoundedCornerShape(30.dp)
                        )
                        .padding(vertical = 5.dp)
                        .width(100.dp),
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.manrope_bold)),
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                )
                Spacer(modifier = Modifier.size(10.dp))
                Text(
                    text = "Get Details",
                    modifier = Modifier
                        .clickable {
                            viewModel.datesNotEmpty = false
                            dataFetch(viewModel)
                            viewModel.fromDate = "05-01-2025"
                            viewModel.toDate = "05-01-2025"
                        }
                        .padding(10.dp)
                        .background(
                            brush = detailsButtonBackGroundGradient,
                            shape = RoundedCornerShape(30.dp)
                        )
                        .border(
                            1.dp,
                            Color.White.copy(alpha = 0.2f),
                            shape = RoundedCornerShape(30.dp)
                        )
                        .padding(vertical = 5.dp, horizontal = 30.dp),
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.manrope_bold)),
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                )
            }
        }
        Text(
            modifier = Modifier.padding(horizontal = 10.dp),
            text = "Trip List",
            style = TextStyle(
                color = Color.White
            )
        )

        if (viewModel.datesNotEmpty) {
            Box(modifier = Modifier.fillMaxSize()){
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "Select Date to View Details",
                    style = TextStyle(color = Color.White, textAlign = TextAlign.Center)
                )
            }
        } else {
            if (viewModel.dataLoading) {
               Box(modifier = Modifier.fillMaxSize()){
                   CircularProgressIndicator(
                       modifier = Modifier.align(Alignment.Center)
                   )
               }
            } else {
                if (viewModel.tripSummary.isEmpty()) {
                    Text(
                        modifier = Modifier.fillMaxSize(),
                        text = viewModel.dataError,
                        style = TextStyle(color = Color.White, textAlign = TextAlign.Center)
                    )
                } else {
                    TripList(viewModel, mainActivity)
                }
            }
        }
    }
}

@Composable
private fun TripList(viewModel: MainViewModel, mainActivity: MainActivity) {

    val tripBackGroundGradient = Brush.verticalGradient(
        listOf(
            Color(0xFF000000).copy(alpha = 0f),
            Color(0xFF76ADFF).copy(alpha = 0.2f)
        )
    )
    Row {
        Row(modifier = Modifier.weight(1f)) {
            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .weight(1f)
            ) {
                Spacer(
                    modifier = Modifier
                        .padding(start = 6.dp)
                        .fillMaxHeight()
                        .width(2.dp)
                        .background(color = Color(0xFF1F57E7))
                )
                LazyColumn(modifier = Modifier) {
                    itemsIndexed(viewModel.tripSummary) { index, trip ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.trip_dot),
                                contentDescription = ""
                            )
                            Text(
                                modifier = Modifier.padding(start = 10.dp),
                                text = trip.StartDate,
                                style = TextStyle(color = Color.White, textAlign = TextAlign.Center)
                            )
                        }
                        Spacer(modifier = Modifier.size(10.dp))
                        Box(
                            modifier = Modifier
                                .clickable {
                                    viewModel.tripData = viewModel.tripSummary[index]
                                    viewModel.tripIndexSelected = index
                                    viewModel.startLocationLat =
                                        viewModel.tripData.StartLocation.split(",")[0].toDouble()
                                    viewModel.startLocationLong =
                                        viewModel.tripData.StartLocation.split(",")[1].toDouble()
                                    viewModel.endLocationLat =
                                        viewModel.tripData.EndLocation.split(",")[0].toDouble()
                                    viewModel.startLocationLong =
                                        viewModel.tripData.EndLocation.split(",")[1].toDouble()
                                    viewModel.changeLocation = true
                                }
                                .padding(start = 20.dp)
                                .background(
                                    brush = tripBackGroundGradient,
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .border(
                                    1.dp,
                                    color = if (viewModel.tripIndexSelected == index) Color(
                                        0xFF1AADFE
                                    ) else Color.Transparent,
                                    shape = RoundedCornerShape(10.dp)
                                )
                        ) {
                            Column {
                                Row {
                                    Text(
                                        modifier = Modifier
                                            .padding(vertical = 15.dp, horizontal = 10.dp)
                                            .background(color = Color(0xFF000106))
                                            .padding(vertical = 5.dp, horizontal = 10.dp),
                                        text = "Trip ${index + 1}",
                                        style = TextStyle(
                                            fontFamily = FontFamily(Font(R.font.manrope_bold)),
                                            color = Color(0xFF89F38D)
                                        )
                                    )
                                    Spacer(modifier = Modifier.weight(1f))
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        Image(
                                            modifier = Modifier
                                                .padding(top = 15.dp)
                                                .align(Alignment.CenterVertically)
                                                .size(30.dp),
                                            painter = painterResource(id = R.drawable.millege),
                                            contentDescription = ""
                                        )
                                        Text(
                                            modifier = Modifier
                                                .padding(horizontal = 10.dp),
                                            text = "${trip.avgSpeedMiles} kmpkh",
                                            style = TextStyle(
                                                fontFamily = FontFamily(Font(R.font.manrope_bold)),
                                                color = Color.White
                                            )
                                        )

                                    }
                                    Spacer(modifier = Modifier.weight(1f))
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Image(
                                            modifier = Modifier
                                                .padding(top = 15.dp)
                                                .align(Alignment.CenterVertically)
                                                .size(30.dp),
                                            painter = painterResource(id = R.drawable.distance),
                                            contentDescription = ""
                                        )
                                        Text(
                                            modifier = Modifier
                                                .padding(horizontal = 10.dp),
                                            text = "${trip.totDistance} km",
                                            style = TextStyle(
                                                fontFamily = FontFamily(Font(R.font.manrope_bold)),
                                                color = Color.White
                                            )
                                        )

                                    }
                                }

                                Row(
                                    modifier = Modifier.padding(10.dp)
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.start__location),
                                        contentDescription = ""
                                    )
                                    Spacer(modifier = Modifier.size(10.dp))
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = trip.StartDate,
                                            style = TextStyle(
                                                fontFamily = FontFamily(Font(R.font.manrope_bold)),
                                                color = Color.White
                                            )
                                        )
                                        Spacer(modifier = Modifier.size(5.dp))
                                        Text(
                                            maxLines = 3,
                                            overflow = TextOverflow.Ellipsis,
                                            text = trip.startAddress,
                                            style = TextStyle(
                                                fontFamily = FontFamily(Font(R.font.manrope_bold)),
                                                color = Color.White
                                            )
                                        )
                                    }
                                    Spacer(modifier = Modifier.size(10.dp))
                                    Image(
                                        painter = painterResource(id = R.drawable.end_location),
                                        contentDescription = ""
                                    )
                                    Spacer(modifier = Modifier.size(10.dp))
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = trip.EndDate,
                                            style = TextStyle(
                                                fontFamily = FontFamily(Font(R.font.manrope_bold)),
                                                color = Color.White
                                            )
                                        )
                                        Spacer(modifier = Modifier.size(5.dp))
                                        Text(
                                            maxLines = 3,
                                            overflow = TextOverflow.Ellipsis,
                                            text = trip.endAddress,
                                            style = TextStyle(
                                                fontFamily = FontFamily(Font(R.font.manrope_bold)),
                                                color = Color.White
                                            )
                                        )
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.size(10.dp))
                    }
                }
            }
        }
        Spacer(modifier = Modifier.size(10.dp))
        TripSummary(modifier = Modifier.weight(2f), viewModel = viewModel, mainActivity)
    }
}

@Composable
private fun TripSummary(modifier: Modifier, viewModel: MainViewModel, mainActivity: MainActivity) {
    Row(modifier = modifier.padding(16.dp)) {
        LazyVerticalStaggeredGrid(
            modifier = Modifier
                .padding(16.dp)
                .weight(1f),
            columns = StaggeredGridCells.Fixed(3)
        ) {
            item {
                TripDataDetails(
                    image = R.drawable.trip_distance,
                    name = "Trip Duration",
                    value = "${viewModel.tripData.totDistanceMiles} kms"
                )
            }
            item {
                TripDataDetails(
                    image = R.drawable.trip_mileage,
                    name = "Trip Mileage",
                    value = "${viewModel.tripData.tripMileage} kmpl"
                )
            }
            item {
                TripDataDetails(
                    image = R.drawable.average_speed,
                    name = "Average Speed",
                    value = "${viewModel.tripData.avgSpeed} km/h"
                )
            }
            item {
                TripDataDetails(
                    image = R.drawable.average_speed,
                    name = "Max Speed",
                    value = "${viewModel.tripData.maxSpeed} km/h"
                )
            }
            item {
                TripDataDetails(
                    image = R.drawable.fuel_consumption,
                    name = "Fuel Consumption",
                    value = "${viewModel.tripData.tripFuelConsumption} Lts"
                )
            }
            item {
                TripDataDetails(
                    image = R.drawable.speeding,
                    name = "Speeding",
                    value = "${viewModel.tripData.speedAlertCount} times"
                )
            }
            item {
                TripDataDetails(
                    image = R.drawable.hard_accelaration,
                    name = "Hard Acceleration",
                    value = "${viewModel.tripData.HAAlertCount} times"
                )
            }
            item {
                TripDataDetails(
                    image = R.drawable.hard_brakes,
                    name = "Hard Brakes",
                    value = "${viewModel.tripData.HBAlertCount} times"
                )
            }
            item {
                TripDataDetails(
                    image = R.drawable.idling,
                    name = "Idling",
                    value = "${viewModel.tripData.idleAlertCount} mins"
                )
            }
        }
        Spacer(modifier = Modifier.size(10.dp))
        MapBox(modifier = Modifier.weight(1f), mainActivity = mainActivity, viewModel = viewModel)
    }
}

@Composable
private fun TripDataDetails(image: Int, name: String, value: String) {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painter = painterResource(id = image), contentDescription = "")
        Spacer(modifier = Modifier.size(5.dp))
        Text(
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            text = name,
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.manrope_bold)),
                color = Color.White
            )
        )
        Spacer(modifier = Modifier.size(5.dp))
        Text(
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            text = value,
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.manrope_bold)),
                color = Color(0xFF3DED4F)
            )
        )
    }
}

private fun dataFetch(viewModel: MainViewModel) {
    viewModel.dataLoading = true
    val okHttpClient = OkHttpClient()
    val request = Request.Builder()
        .url("https://cvdrivenostics.mytvs.in/movement/tripSummaryReportNew?orgId=109991&vehicleId=26272&dateFrom=2025-01-05%2000:00:00&dateTo=2025-01-05%2023:59:59")
        .build()

    okHttpClient.newCall(request).enqueue(object : okhttp3.Callback {
        override fun onFailure(call: Call, e: IOException) {
            viewModel.dataError = e.message.toString()
        }

        override fun onResponse(call: Call, response: Response) {
            val responseBody = response.body?.string()
            val gson = Gson()
            viewModel.tripSummary = gson.fromJson(responseBody, TripData::class.java)
            viewModel.tripData = viewModel.tripSummary.get(0)
            for(i in 0 until viewModel.tripSummary.size){
                getStartlocationAddress(viewModel.tripSummary.get(i).StartLocation,viewModel.tripSummary.get(i).EndLocation,i,viewModel)
            }
            viewModel.startLocationLat = viewModel.tripData.StartLocation.split(",")[0].toDouble()
            viewModel.startLocationLong = viewModel.tripData.StartLocation.split(",")[1].toDouble()
            viewModel.endLocationLat = viewModel.tripData.EndLocation.split(",")[0].toDouble()
            viewModel.endLocationLong = viewModel.tripData.EndLocation.split(",")[1].toDouble()
            viewModel.dataLoading = false
        }
    })
}

private fun getStartlocationAddress(
    startLocation: String,
    endLocation: String,
    index: Int,
    viewModel: MainViewModel
) {
    var startLocationLat = startLocation.split(",")[0].toDouble()
    var startLocationLong = startLocation.split(",")[1].toDouble()
    val okHttpClient = OkHttpClient()
    val request = Request.Builder()
        .url("https://apis.mappls.com/advancedmaps/v1/4a0bbbe5ab800eca852f98bc67c7313b/rev_geocode?lat=${startLocationLat}&lng=${startLocationLong}")
        .build()

    okHttpClient.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {

        }

        override fun onResponse(call: Call, response: Response) {
            val responseBody = response.body?.string()
            val gson = Gson()
            if (response.code == 200) {
                val addressResponse = gson.fromJson(responseBody, AddressModel::class.java)
                if (addressResponse != null) {
                    viewModel.tripSummary[index].startAddress = addressResponse.results[0].formatted_address
                    getEndLocationAddress(startLocation,endLocation,index,viewModel)
                } else {

                }
            } else {

            }
        }
    })
}

private fun getEndLocationAddress(
    startLocation: String,
    endLocation: String,
    index: Int,
    viewModel: MainViewModel
) {
    var endLocationLat = endLocation.split(",")[0].toDouble()
    var endLocationLong = endLocation.split(",")[1].toDouble()
    val okHttpClient = OkHttpClient()
    val request = Request.Builder()
        .url("https://apis.mappls.com/advancedmaps/v1/4a0bbbe5ab800eca852f98bc67c7313b/rev_geocode?lat=${endLocationLat}&lng=${endLocationLong}")
        .build()

    okHttpClient.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {

        }

        override fun onResponse(call: Call, response: Response) {
            val responseBody = response.body?.string()
            val gson = Gson()
            if (response.code == 200) {
                val addressResponse = gson.fromJson(responseBody, AddressModel::class.java)
                if (addressResponse != null) {
                    viewModel.tripSummary[index].endAddress = addressResponse.results[0].formatted_address
                } else {

                }
            } else {

            }
        }
    })
}