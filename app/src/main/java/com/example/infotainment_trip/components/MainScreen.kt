package com.example.infotainment_trip.components

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
import java.text.SimpleDateFormat
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.TimeZone


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel,
    mainActivity: MainActivity
) {
    val mContext = LocalContext.current

    val mCalendar = Calendar.getInstance()

    val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    val today = Calendar.getInstance()
    val timeZone = TimeZone.getTimeZone("Asia/Kolkata")

    val startOfLastWeek = Calendar.getInstance(timeZone).apply {
        firstDayOfWeek = Calendar.MONDAY
        set(Calendar.WEEK_OF_YEAR, today.get(Calendar.WEEK_OF_YEAR) - 1)
        set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
    }.time

    val endOfLastWeek = Calendar.getInstance(timeZone).apply {
        firstDayOfWeek = Calendar.MONDAY
        set(Calendar.WEEK_OF_YEAR, today.get(Calendar.WEEK_OF_YEAR) - 1)
        set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
    }.time


    val thisMonth = YearMonth.now()
    val lastMonth = thisMonth.minusMonths(1)
    val monthYearFormatter = DateTimeFormatter.ofPattern("yyy-MM")


    mCalendar.time = Date()


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

    var buttonStroke = Brush.linearGradient(
        listOf(
            Color(0xFFFFFFFF).copy(alpha = 1f),
            Color(0xFFFFFFFF).copy(alpha = 0f),
            Color(0xFFFFFFFF).copy(alpha = 0f),
            Color(0xFFFFFFFF).copy(alpha = 1f)
        )
    )

    Box(modifier = Modifier.background(brush = mainBackGroundGradient)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = backgroundGradient)
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
                        color = Color.White,
                    )
                )

                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Last week",
                        modifier = Modifier
                            .clickable {
                                viewModel.datesNotEmpty = false
                                dataFetch(
                                    viewModel,
                                    dateFormat.format(startOfLastWeek),
                                    dateFormat.format(endOfLastWeek)
                                )
                            }
                            .padding(10.dp)
                            .background(
                                brush = detailsButtonBackGroundGradient,
                                shape = RoundedCornerShape(30.dp)
                            )
                            .border(1.dp, brush = buttonStroke, shape = RoundedCornerShape(20.dp))
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
                        text = "Last Month",
                        modifier = Modifier
                            .clickable {
                                viewModel.datesNotEmpty = false

                                dataFetch(
                                    viewModel,
                                    "${lastMonth.format(monthYearFormatter)}-01",
                                    "${lastMonth.format(monthYearFormatter)}-30"
                                )
                            }
                            .padding(10.dp)
                            .background(
                                brush = detailsButtonBackGroundGradient,
                                shape = RoundedCornerShape(30.dp)
                            )
                            .border(1.dp, brush = buttonStroke, shape = RoundedCornerShape(20.dp))
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
                        text = "Yesterday",
                        modifier = Modifier
                            .clickable {
                                viewModel.datesNotEmpty = false
                                val cal = Calendar.getInstance()
                                cal.add(Calendar.DATE, -1);
                                dataFetch(
                                    viewModel,
                                    dateFormat.format(cal.time),
                                    dateFormat.format(cal.time)
                                )
                            }
                            .padding(10.dp)
                            .background(
                                brush = detailsButtonBackGroundGradient,
                                shape = RoundedCornerShape(30.dp)
                            )
                            .border(1.dp, brush = buttonStroke, shape = RoundedCornerShape(20.dp))
                            .padding(vertical = 5.dp)
                            .width(100.dp),
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
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "Select Date to View Details",
                        style = TextStyle(
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            fontFamily = FontFamily(Font(R.font.manrope_semibold)),
                        )
                    )
                }
            } else {
                if (viewModel.dataLoading) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                } else {
                    if (viewModel.tripSummary.isEmpty()) {
                        Text(
                            modifier = Modifier.fillMaxSize(),
                            text = viewModel.dataError,
                            style = TextStyle(
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                fontFamily = FontFamily(Font(R.font.manrope_extrabold)),
                            )
                        )
                    } else {
                        TripList(viewModel, mainActivity)
                    }
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
                        .padding(start = 7.dp, top = 10.dp)
                        .fillMaxHeight()
                        .width(2.dp)
                        .background(color = Color(0xFF1F57E7))
                )
                LazyColumn(modifier = Modifier) {
                    itemsIndexed(viewModel.tripSummary) { index, trip ->
                        trip?.let {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.trip_dot),
                                    contentDescription = ""
                                )
                                trip.StartDate?.let { it1 ->
                                    Text(
                                        modifier = Modifier.padding(start = 10.dp),
                                        text = it1.split(" ").take(2).toString().replace("]", "")
                                            .replace("[", "").replace(",", ""),
                                        style = TextStyle(
                                            fontSize = 10.sp,
                                            color = Color.White,
                                            textAlign = TextAlign.Center,
                                            fontFamily = FontFamily(Font(R.font.manrope_bold)),
                                        )
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.size(10.dp))
                            Box(
                                modifier = Modifier
                                    .clickable {
                                        viewModel.tripData = viewModel.tripSummary[index]


                                        viewModel.tripIndexSelected = index
                                        viewModel.startLocationLat =
                                            viewModel.tripData.StartLocation
                                                ?.split(",")
                                                ?.get(0)
                                                ?.toDouble()!!
                                        viewModel.startLocationLong =
                                            viewModel.tripData.StartLocation
                                                ?.split(",")
                                                ?.get(1)
                                                ?.toDouble()!!
                                        viewModel.endLocationLat =
                                            viewModel.tripData.EndLocation
                                                ?.split(",")
                                                ?.get(0)
                                                ?.toDouble()!!
                                        viewModel.startLocationLong =
                                            viewModel.tripData.EndLocation
                                                ?.split(",")
                                                ?.get(1)
                                                ?.toDouble()!!
                                        viewModel.changeLocation = true


                                        viewModel.tripData.StartLocation?.let {
                                            viewModel.tripData.EndLocation?.let { it1 ->
                                                getStartlocationAddress(
                                                    it,
                                                    it1,
                                                    0,
                                                    viewModel
                                                )
                                            }
                                        }
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
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            modifier = Modifier
                                                .weight(0.7f)
                                                .padding(
                                                    top = 15.dp,
                                                    bottom = 15.dp,
                                                    start = 10.dp
                                                )
                                                .background(color = Color(0xFF000106))
                                                .padding(vertical = 5.dp, horizontal = 10.dp),
                                            text = "Trip ${index + 1}",
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis,
                                            style = TextStyle(
                                                fontSize = 8.sp,
                                                fontFamily = FontFamily(Font(R.font.manrope_bold)),
                                                color = Color(0xFF89F38D)
                                            )
                                        )
                                        Spacer(modifier = Modifier.weight(0.1f))
                                        Row(
                                            modifier = Modifier.weight(1f),
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
                                                    .padding(end = 10.dp),
                                                text = "${trip.avgSpeed} kmpkh",
                                                maxLines = 1,
                                                overflow = TextOverflow.Ellipsis,
                                                style = TextStyle(
                                                    fontSize = 8.sp,
                                                    fontFamily = FontFamily(Font(R.font.manrope_bold)),
                                                    color = Color.White
                                                )
                                            )

                                        }
                                        Spacer(modifier = Modifier.weight(0.2f))
                                        Row(
                                            modifier = Modifier.weight(1f),
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
                                                    .padding(end = 10.dp),
                                                text = "${trip.totDistance} km",
                                                maxLines = 1,
                                                overflow = TextOverflow.Ellipsis,
                                                style = TextStyle(
                                                    fontSize = 8.sp,
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
        }
        TripSummary(modifier = Modifier.weight(2f), viewModel = viewModel, mainActivity)
    }
}

@Composable
private fun TripSummary(modifier: Modifier, viewModel: MainViewModel, mainActivity: MainActivity) {
    Row(modifier = modifier.padding(end = 16.dp)) {
        viewModel.tripData?.let {
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.padding(10.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.start__location),
                        contentDescription = ""
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        viewModel.tripData.StartDate?.let { it1 ->
                            Text(
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                text = it1.split(" ").takeLast(2).toString()
                                    .replace("[", "").replace("]", "")
                                    .replace(",", ""),
                                style = TextStyle(
                                    fontSize = 10.sp,
                                    fontFamily = FontFamily(Font(R.font.manrope_bold)),
                                    color = Color.White
                                )
                            )
                        }
                        Spacer(modifier = Modifier.size(5.dp))
//                        viewModel.tripData.startAddress?.let { it1 ->
                            Text(
                                maxLines = 3,
                                overflow = TextOverflow.Ellipsis,
                                text = "J4XC+GFC, Thirumagal ByPass, Konerikarai, Kandhampatty, Tamil Nadu 636005, India",
                                style = TextStyle(
                                    fontSize = 10.sp,
                                    fontFamily = FontFamily(Font(R.font.manrope_regular)),
                                    color = Color.White
                                )
                            )
//                        }
                    }
                    Spacer(modifier = Modifier.size(10.dp))
                    Image(
                        painter = painterResource(id = R.drawable.end_location),
                        contentDescription = ""
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        viewModel.tripData.EndDate?.let { it1 ->
                            Text(
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                text = it1.split(" ").takeLast(2).toString()
                                    .replace("[", "").replace("]", "")
                                    .replace(",", ""),
                                style = TextStyle(
                                    fontSize = 10.sp,
                                    fontFamily = FontFamily(Font(R.font.manrope_bold)),
                                    color = Color.White
                                )
                            )
                        }
                        Spacer(modifier = Modifier.size(5.dp))
//                        viewModel.tripData.endAddress?.let { it1 ->
                            Text(
                                maxLines = 3,
                                overflow = TextOverflow.Ellipsis,
                                text = "J4XC+GFC, Thirumagal ByPass, Konerikarai, Kandhampatty, Tamil Nadu 636005, India\n",
                                style = TextStyle(
                                    fontSize = 10.sp,
                                    fontFamily = FontFamily(Font(R.font.manrope_regular)),
                                    color = Color.White
                                )
                            )
//                        }
                    }
                }
                LazyVerticalStaggeredGrid(
                    modifier = Modifier
                        .padding(16.dp),
                    columns = StaggeredGridCells.Fixed(3)
                ) {
                    item {
                        TripDataDetails(
                            image = R.drawable.trip_distance,
                            name = "Total Distance",
                            value = "${viewModel.tripData.totDistance} kms"
                        )
                    }
                    item {
                        TripDataDetails(
                            image = R.drawable.trip_mileage,
                            name = "Trip Mileage",
                            value = "${viewModel.tripData.mileageMaf} kmpl"
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
                            value = "${viewModel.tripData.fuelConsumedMaf} Lts"
                        )
                    }
                    item {
                        TripDataDetails(
                            image = R.drawable.hard_accelaration,
                            name = "Driver Score",
                            value = "${viewModel.tripData.driverScore}"
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.size(10.dp))
            MapBox(
                modifier = Modifier.weight(1f),
                mainActivity = mainActivity,
                viewModel = viewModel
            )
        }
    }
}

@Composable
private fun TripDataDetails(image: Int, name: String, value: String) {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(20.dp),
            painter = painterResource(id = image),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.size(5.dp))
        Text(
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            text = name,
            style = TextStyle(
                fontSize = 10.sp,
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
                fontSize = 10.sp,
                fontFamily = FontFamily(Font(R.font.manrope_bold)),
                color = Color(0xFF3DED4F)
            )
        )
    }
}

private fun dataFetch(viewModel: MainViewModel, fromDate: String, toDate: String) {
    viewModel.dataLoading = true
    val okHttpClient = OkHttpClient()
    val request = Request.Builder()
//        .url("https://cvdrivenostics.mytvs.in/movement/tripSummaryReportNew?orgId=109991&vehicleId=26272&dateFrom=${fromDate}%2000:00:00&dateTo=${toDate}%2023:59:59")
        .url(" https://cvdrivenostics.mytvs.in/movement/tripSummaryReportMap?&orgId=109991&vehicleId=26224&startDate=${fromDate}%2000:00:00&endDate=${toDate}%2023:59:59&driverID=0&driverSearch=0&customerID=109990&regionId=0&length=10&dealerId=0")

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
            viewModel.tripData.StartLocation?.let {
                viewModel.tripData.EndLocation?.let { it1 ->
                    getStartlocationAddress(
                        it,
                        it1,
                        0,
                        viewModel
                    )
                }
            }
            for (i in 0 until viewModel.tripSummary.size) {
                viewModel.tripSummary?.get(i)?.StartLocation?.let {
                    viewModel.tripSummary.get(i).EndLocation?.let { it1 ->
                        getStartlocationAddress(
                            it,
                            it1,
                            i,
                            viewModel
                        )
                    }
                }
                if (i == viewModel.tripSummary.size - 1) {
                }
                viewModel.dataLoading = false
            }
            viewModel.startLocationLat =
                viewModel.tripData.StartLocation?.split(",")?.get(0)?.toDouble()!!
            viewModel.startLocationLong =
                viewModel.tripData.StartLocation?.split(",")?.get(1)?.toDouble()!!
            viewModel.endLocationLat =
                viewModel.tripData.EndLocation?.split(",")?.get(0)?.toDouble()!!
            viewModel.endLocationLong =
                viewModel.tripData.EndLocation?.split(",")?.get(1)?.toDouble()!!
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
                if (addressResponse.results[0].formatted_address != null) {
                    viewModel.tripSummary[index].startAddress =
                        addressResponse.results[0].formatted_address
                    getEndLocationAddress(startLocation, endLocation, index, viewModel)
                } else {
                    viewModel.tripSummary[index].startAddress = "No Address"
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
                if (addressResponse.results[0].formatted_address != null) {
                    viewModel.tripSummary[index].endAddress =
                        addressResponse.results[0].formatted_address
                } else {
                    viewModel.tripSummary[index].endAddress = "No Address"
                }
            } else {

            }
        }
    })
}