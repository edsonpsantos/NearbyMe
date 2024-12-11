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
import androidx.compose.runtime.LaunchedEffect
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
    uiState: HomeUIState = HomeUIState(),
    onEvent: (HomeUIEvent) -> Unit,
    onNavigateToMarketDetails: (Market) -> Unit
) {
    val bottomSheetState = rememberBottomSheetScaffoldState()
    var isBottomSheetOpen by remember { mutableStateOf(true) }

    val configuration = LocalConfiguration.current

    LaunchedEffect(key1 = true) {
        onEvent(HomeUIEvent.onFetchCategories)
    }

    if (isBottomSheetOpen) {
        BottomSheetScaffold(
            modifier = modifier,
            scaffoldState = bottomSheetState,
            sheetContainerColor = Gray100,
            sheetPeekHeight = configuration.screenHeightDp.dp * 0.5f,
            sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
            sheetContent = {

                if (!uiState.markets.isNullOrEmpty()) {
                    NearbyMarketCardList(
                        modifier = modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        markets = uiState.markets,
                        onMarketClick = { selectedMarket ->
                            onNavigateToMarketDetails(selectedMarket)
                        }
                    )
                }
            },
            content = {
                Box(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(it)
                ) {
                    GoogleMap(modifier = modifier.fillMaxSize())

                    if (!uiState.categories.isNullOrEmpty()) {
                        NearbyCategoryFilterChipList(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(top = 24.dp)
                                .align(Alignment.TopStart),
                            categories = uiState.categories,
                            onSelectedCategoryChange = { selectedCategory ->
                                onEvent(HomeUIEvent.onFetchMarkets(selectedCategory.id))
                            },
                        )
                    }
                }
            }
        )
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(uiState = HomeUIState(), onEvent = {}, onNavigateToMarketDetails = {})
}


