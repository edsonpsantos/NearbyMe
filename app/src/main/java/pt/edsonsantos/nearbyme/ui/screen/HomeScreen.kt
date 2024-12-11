package pt.edsonsantos.nearbyme.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.maps.android.compose.GoogleMap
import pt.edsonsantos.nearbyme.data.model.Market
import pt.edsonsantos.nearbyme.data.model.mock.MockCategories
import pt.edsonsantos.nearbyme.data.model.mock.MockMarkets
import pt.edsonsantos.nearbyme.ui.components.category.NearbyCategoryFilterChipList
import pt.edsonsantos.nearbyme.ui.components.market.NearbyMarketCardList
import pt.edsonsantos.nearbyme.ui.theme.Gray100

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onNavigateToMarketDetails: (Market)-> Unit
) {
    val bottomSheetState = rememberBottomSheetScaffoldState()
    var isBottomSheetOpen by remember { mutableStateOf(true) }

    val configuration = LocalConfiguration.current

    if (isBottomSheetOpen) {
        BottomSheetScaffold(
            modifier = modifier,
            scaffoldState = bottomSheetState,
            sheetContainerColor = Gray100,
            sheetPeekHeight = configuration.screenHeightDp.dp * 0.5f,
            sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
            sheetContent = {
                NearbyMarketCardList(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    markets = MockMarkets,
                    onMarketClick = { selectedMarket ->
                        onNavigateToMarketDetails(selectedMarket)
                    }
                )
            },
            content = {
                Box(modifier = modifier
                    .fillMaxSize()
                    .padding(it)) {
                    GoogleMap(modifier = modifier.fillMaxSize())
                    NearbyCategoryFilterChipList(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp)
                            .align(Alignment.TopStart),
                        categories = MockCategories,
                        onSelectedCategoryChange = {},
                    )
                }
            }
        )
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(onNavigateToMarketDetails={})
}

/*
@Composable
fun OpenStreetMapView() {

    // AndroidView para renderizar o MapView
    AndroidView(
        factory = { context ->
            Configuration.getInstance().load(context, context.getSharedPreferences("osmdroid", 0))

            MapView(context).apply {
                setTileSource(org.osmdroid.tileprovider.tilesource.TileSourceFactory.MAPNIK)

                // Configurar o ponto inicial do mapa
                controller.setZoom(15.0)
                controller.setCenter(GeoPoint(37.7749, -122.4194))

                // Adicionar um marcador
                val marker = Marker(this)
                marker.position = GeoPoint(37.7749, -122.4194)
                marker.title = "Marcador Exemplo"

                val iconRes: Drawable = context.getDrawable(R.drawable.ic_map_pin)!!
                val width = 32
                val height = 32
                iconRes.setBounds(0, 0, width, height)
                iconRes.setTint(RedBase.hashCode())

                marker.icon = iconRes
                overlays.add(marker)
            }
        },
        update = { mapView ->
            mapView.onResume()
        },
        modifier = Modifier
    )
}

@Preview(showBackground = true)
@Composable
fun OpenStreetMapPreview() {
    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray),
            contentAlignment = Alignment.Center
        ) {
            Text("Simulação do mapa", color = Color.White)
        }
    }
}
*/

