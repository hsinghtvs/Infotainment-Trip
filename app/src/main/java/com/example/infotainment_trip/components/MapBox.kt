package com.example.infotainment_trip.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.infotainment_trip.MainActivity
import com.example.infotainment_trip.R
import com.example.infotainment_trip.viewModel.MainViewModel


@Composable
fun MapBox(modifier: Modifier, mainActivity: MainActivity, viewModel: MainViewModel) {
    Column(modifier = modifier.padding(bottom = 10.dp)) {
        Spacer(modifier = Modifier.size(10.dp))
        Box(modifier = Modifier) {
            MapComponent(
                mainActivity = mainActivity,
                modifier = Modifier
                    .background(
                        color = Color.Transparent, shape = RoundedCornerShape(10.dp)
                    )
                    .border(
                        width = 0.dp,
                        shape = RoundedCornerShape(10.dp),
                        color = Color.Transparent
                    )
            )
        }
        if (viewModel.tripData != null) {
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
                        text = viewModel.tripData.StartDate,
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.manrope_bold)),
                            color = Color.White
                        )
                    )
                    Spacer(modifier = Modifier.size(5.dp))
                    Text(
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        text = if(viewModel.tripData.startAddress != null ) viewModel.tripData.startAddress else "No Address Found",
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
                        text = viewModel.tripData.EndDate,
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.manrope_bold)),
                            color = Color.White
                        )
                    )
                    Spacer(modifier = Modifier.size(5.dp))
                    Text(
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        text = if(viewModel.tripData.endAddress != null ) viewModel.tripData.endAddress else "No Address Found",
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.manrope_bold)),
                            color = Color.White
                        )
                    )
                }
            }
        }
    }
}