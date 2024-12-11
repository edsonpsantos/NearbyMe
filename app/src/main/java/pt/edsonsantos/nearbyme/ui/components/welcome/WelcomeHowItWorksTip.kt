package pt.edsonsantos.nearbyme.ui.components.welcome

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pt.edsonsantos.nearbyme.R
import pt.edsonsantos.nearbyme.ui.theme.Gray500
import pt.edsonsantos.nearbyme.ui.theme.RedBase
import pt.edsonsantos.nearbyme.ui.theme.Typography


@Composable
fun WelcomeHowItWorksTip(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String,
    @DrawableRes iconRes: Int
) {
    Row(
        modifier = Modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Icon(
            modifier = modifier.size(32.dp),
            painter = painterResource(id = iconRes),
            tint = RedBase,
            contentDescription = "Icone como funciona"
        )
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ){
            Text(title, style = Typography.headlineSmall)
            Text(subTitle, color= Gray500, style = Typography.bodySmall)
        }
    }
}

@Preview
@Composable
private fun WelcomeHowItWorksTipPreview() {
    WelcomeHowItWorksTip(
        title = "Encontre estabelecimentos",
        subTitle = "Veja locais perto de você, que são parceiros NearBy",
        iconRes = R.drawable.ic_map_pin
    )
}