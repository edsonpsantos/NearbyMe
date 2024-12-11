package pt.edsonsantos.nearbyme.ui.components.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pt.edsonsantos.nearbyme.R
import pt.edsonsantos.nearbyme.ui.theme.Typography
@Composable
fun WelcomeContent(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ){
        Text("Veja como funciona:", style = Typography.bodyLarge)
        WelcomeHowItWorksTip(
            title = "Encontre estabelecimentos",
            subTitle = "Veja locais perto de você, que são parceiros NearBy",
            iconRes = R.drawable.ic_map_pin
        )

        WelcomeHowItWorksTip(
            title = "Ative cupom com QR Code",
            subTitle = "Escaneie o código do estableciomento para usar o benefício",
            iconRes = R.drawable.ic_qrcode
        )
        WelcomeHowItWorksTip(
            title = "Garanta vantagens perto de você",
            subTitle = "Ative cupons onde estiver, em diferentes tipos de estabeleciomentos",
            iconRes = R.drawable.ic_ticket
        )
    }
}

@Preview
@Composable
private fun WelcomeContentPreview() {
    WelcomeContent()
}