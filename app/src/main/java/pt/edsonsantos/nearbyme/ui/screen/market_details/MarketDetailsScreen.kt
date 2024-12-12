package pt.edsonsantos.nearbyme.ui.screen.market_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import pt.edsonsantos.nearbyme.R
import pt.edsonsantos.nearbyme.data.model.Market
import pt.edsonsantos.nearbyme.data.model.mock.MockMarkets
import pt.edsonsantos.nearbyme.ui.components.button.NearbyMeButton
import pt.edsonsantos.nearbyme.ui.components.marketdetails.MarketDetailsInfo
import pt.edsonsantos.nearbyme.ui.components.marketdetails.NearbyMarketDetailsCupons
import pt.edsonsantos.nearbyme.ui.components.marketdetails.NearbyMarketDetailsRules
import pt.edsonsantos.nearbyme.ui.theme.Typography

@Composable
fun MarketDetailsScreen(
    modifier: Modifier = Modifier,
    uiState: MarketDetailsUIState,
    onEvent: (MarketDetailsUIEvent) -> Unit,
    onNavigateToQRCodeScanner: ()-> Unit,
    market: Market,
    onNavigateBack: () -> Unit
) {

    LaunchedEffect(true) {
        onEvent(MarketDetailsUIEvent.onFetchRules(marketId = market.id))
    }

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        AsyncImage(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f),
            contentDescription = "Image do local",
            contentScale = ContentScale.Crop,
            model = market.cover
        )

        Box(
            modifier = modifier
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                .fillMaxWidth()
                .fillMaxHeight(0.75f)
                .align(Alignment.BottomCenter)
                .background(Color.White)
        ) {
            Column(
                modifier = modifier
                    .fillMaxHeight()
                    .padding(36.dp)
            ) {
                Column {
                    Text(market.name, style = Typography.headlineLarge)
                    Spacer(modifier = modifier.height(16.dp))
                    Text(market.description, style = Typography.bodyMedium)
                }
                Spacer(modifier = modifier.height(32.dp))

                Column(
                    modifier = modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                ) {

                    MarketDetailsInfo(market = market)

                    HorizontalDivider(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(vertical = 24.dp)
                    )

                    if(!uiState.rules.isNullOrEmpty()){
                        NearbyMarketDetailsRules(rules = uiState.rules)
                        HorizontalDivider(modifier = modifier
                            .fillMaxWidth()
                            .padding(vertical = 24.dp)
                        )
                    }
                    if(!uiState.coupon.isNullOrEmpty()) {
                        NearbyMarketDetailsCupons(coupons = listOf(uiState.coupon))
                    }
                }

                NearbyMeButton(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    text = "Ler QR Code",
                    onClick = onNavigateToQRCodeScanner
                )
            }
        }

        NearbyMeButton(
            modifier = modifier
                .align(Alignment.TopStart)
                .padding(top = 24.dp, start = 24.dp),
            iconRes = R.drawable.ic_arrow_left,
            onClick = onNavigateBack
        )
    }

}

@Preview
@Composable
private fun MarketDetailsScreenPreview() {
    MarketDetailsScreen(
        modifier = Modifier,
        market = MockMarkets.first(),
        uiState = MarketDetailsUIState(),
        onEvent = {},
        onNavigateToQRCodeScanner={},
        onNavigateBack = {}
    )
}