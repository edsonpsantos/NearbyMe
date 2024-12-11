package pt.edsonsantos.nearbyme.ui.components.marketdetails

import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pt.edsonsantos.nearbyme.R
import pt.edsonsantos.nearbyme.data.model.Market
import pt.edsonsantos.nearbyme.data.model.mock.MockMarkets
import pt.edsonsantos.nearbyme.ui.theme.Gray400
import pt.edsonsantos.nearbyme.ui.theme.Gray500
import pt.edsonsantos.nearbyme.ui.theme.Typography

@Composable
fun MarketDetailsInfo(
    modifier: Modifier = Modifier,
    market: Market
) {
    Column(
        modifier = modifier,
        verticalArrangement = spacedBy(16.dp)
    ) {
        Text(
            text = "Informações",
            style = Typography.headlineSmall,
            color = Gray400
        )

        Column(
            verticalArrangement = spacedBy(8.dp)
        ) {
            Row(
                horizontalArrangement = spacedBy(8.dp)
            ) {
                Icon(
                    modifier = modifier.size(16.dp),
                    painter = painterResource(id = R.drawable.ic_ticket),
                    tint = Gray500,
                    contentDescription = "Icone de cupons"
                )
                Text(
                    text = "${market.coupons} cupons disponíveis",
                    style = Typography.labelMedium,
                    color = Gray500
                )
            }

            Row(
                horizontalArrangement = spacedBy(8.dp)
            ) {
                Icon(
                    modifier = modifier.size(16.dp),
                    painter = painterResource(id = R.drawable.ic_map_pin),
                    tint = Gray500,
                    contentDescription = "Icone de Endereço"
                )
                Text(
                    text = market.address,
                    style = Typography.labelMedium,
                    color = Gray500
                )
            }

            Row(
                horizontalArrangement = spacedBy(8.dp)
            ) {
                Icon(
                    modifier = modifier.size(16.dp),
                    painter = painterResource(id = R.drawable.ic_phone),
                    tint = Gray500,
                    contentDescription = "Icone de Telefone"
                )
                Text(
                    text = market.phone,
                    style = Typography.labelMedium,
                    color = Gray500
                )
            }
        }
    }
}

@Preview
@Composable
private fun MarketDetailsInfoPreview() {
    MarketDetailsInfo(
        market = MockMarkets.first()
    )
}