package pt.edsonsantos.nearbyme.ui.components.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.internal.toImmutableList
import pt.edsonsantos.nearbyme.R
import pt.edsonsantos.nearbyme.data.model.mock.MockUserLocation
import pt.edsonsantos.nearbyme.ui.screen.home.HomeUIState
import pt.edsonsantos.nearbyme.ui.util.findNortheastPoints
import pt.edsonsantos.nearbyme.ui.util.findSouthwestPoints
import kotlin.math.roundToInt


@Composable
fun NearbyMeGoogleMaps(modifier: Modifier = Modifier, uiState: HomeUIState) {

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(MockUserLocation, 13f)
    }
    val uiSettings by remember {
        mutableStateOf(
            MapUiSettings(
                zoomControlsEnabled = true
            )
        )
    }

    val context = LocalContext.current
    val couroutineScope = rememberCoroutineScope()
    val density = LocalDensity.current


    GoogleMap(
        modifier = modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        uiSettings = uiSettings
    ) {
        context.getDrawable(R.drawable.ic_user_location)?.let {
            Marker(
                state = MarkerState(position = MockUserLocation),
                icon = BitmapDescriptorFactory.fromBitmap(
                    it.toBitmap(
                        width = density.run { 72.dp.toPx() }.roundToInt(),
                        height = density.run { 72.dp.toPx().roundToInt() }
                    )
                )
            )
        }

        if (!uiState.markets.isNullOrEmpty()) {
            context.getDrawable(R.drawable.img_pin)?.let {
                uiState.marketLocations?.toImmutableList()
                    ?.forEachIndexed { index, location ->
                        Marker(
                            state = MarkerState(position = location),
                            icon = BitmapDescriptorFactory.fromBitmap(
                                it.toBitmap(
                                    width = density.run { 36.dp.toPx() }
                                        .roundToInt(),
                                    height = density.run {
                                        36.dp.toPx().roundToInt()
                                    }
                                )
                            ),
                            title = uiState.markets[index].name
                        )
                    }.also {

                        couroutineScope.launch {
                            val allMarks = uiState.marketLocations?.plus(
                                MockUserLocation
                            )
                            val southwestPoint =
                                findSouthwestPoints(points = allMarks.orEmpty())
                            val northeastPoint =
                                findNortheastPoints(points = allMarks.orEmpty())

                            val centerPointLatitude =
                                (southwestPoint.latitude + northeastPoint.latitude) / 2
                            val centerPointLongitude =
                                (southwestPoint.longitude + northeastPoint.longitude) / 2

                            val cameraUpdate =
                                CameraUpdateFactory.newCameraPosition(
                                    CameraPosition(
                                        LatLng(
                                            centerPointLatitude,
                                            centerPointLongitude,
                                        ),
                                        13f,
                                        0f,
                                        0f
                                    )
                                )
                            delay(200)
                            cameraPositionState.animate(
                                cameraUpdate,
                                durationMs = 500
                            )
                        }
                    }
            }
        }
    }
}
