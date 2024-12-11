package pt.edsonsantos.nearbyme.ui.components.market

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.edsonsantos.nearbyme.R
import pt.edsonsantos.nearbyme.data.model.Market
import pt.edsonsantos.nearbyme.ui.theme.Gray100
import pt.edsonsantos.nearbyme.ui.theme.Gray200
import pt.edsonsantos.nearbyme.ui.theme.Gray400
import pt.edsonsantos.nearbyme.ui.theme.Gray500
import pt.edsonsantos.nearbyme.ui.theme.RedBase
import pt.edsonsantos.nearbyme.ui.theme.Typography


@Composable
fun NearbyMarketCard(
    modifier: Modifier = Modifier,
    market: Market,
    onClick: (Market) -> Unit
) {

    Card(modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Gray100)
            .border(width = 1.dp,
                    color = Gray200,
                    shape = RoundedCornerShape(12.dp)
            ),
            onClick = {
                onClick(market)
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Gray100)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Image(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .fillMaxWidth(0.3f)
                    .height(IntrinsicSize.Min)
                    .aspectRatio(ratio = 1f, matchHeightConstraintsFirst = true),
                contentScale = ContentScale.Crop,
                painter = painterResource(R.drawable.img_brunch),
                contentDescription = "Imagem do estabelecimento"
            )

            Column() {
                Text(
                    text = market.name,
                    style = Typography.headlineSmall.copy(fontSize = 14.sp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = market.description,
                    color = Gray500,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = Typography.bodyMedium.copy(fontSize = 12.sp)
                )
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        tint = if (market.coupons > 0) RedBase else Gray400,
                        painter = painterResource(id = R.drawable.ic_ticket),
                        contentDescription = "Icone de desconto"
                    )

                    Text(
                        text = "${market.coupons} cupons disponíveis",
                        color = Gray400,
                        style = Typography.bodyMedium.copy(fontSize = 12.sp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun NearbyMarketCardWithCuponsPreview() {
    NearbyMarketCard(
        modifier = Modifier.fillMaxWidth(),
        market = Market(
            id = "1",
            categoryId = "1",
            name = "Restaurante do Zé",
            description = "Restaurante do Zé, a melhor comida margem sul",
            coupons = 10,
//            rules = emptyList(),
            latitude = -23.55970,
            longitude = -46.6581,
            address = "Rua dos Restaurantes, 0",
            phone = "(11) 99999-9999","https://images.unsplash.com/photo-1542831371-29b0f74f9713?w=400&h=300"
        ),
        onClick = {}
    )
}

@Preview
@Composable
private fun NearbyMarketCardNoCuponsPreview() {
    NearbyMarketCard(
        modifier = Modifier.fillMaxWidth(),
        market = Market(
            id = "1",
            categoryId = "1",
            name = "Restaurante do Zé",
            description = "Restaurante do Zé, a melhor comida margem sul",
            coupons = 0,
//            rules = emptyList(),
            latitude = -23.55970,
            longitude = -46.6581,
            address = "Rua dos Restaurantes, 0",
            phone = "(11) 99999-9999","https://images.unsplash.com/photo-1542831371-29b0f74f9713?w=400&h=300"
        ),
        onClick = {}
    )
}