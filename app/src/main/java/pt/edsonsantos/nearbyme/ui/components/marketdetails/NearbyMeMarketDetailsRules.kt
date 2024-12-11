package pt.edsonsantos.nearbyme.ui.components.marketdetails

import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.edsonsantos.nearbyme.data.model.Rules
import pt.edsonsantos.nearbyme.data.model.mock.MockRules
import pt.edsonsantos.nearbyme.ui.theme.Gray400
import pt.edsonsantos.nearbyme.ui.theme.Gray500
import pt.edsonsantos.nearbyme.ui.theme.Typography

@Composable
fun NearbyMarketDetailsRules(
    modifier: Modifier = Modifier,
    rules: List<Rules>
) {
    Column(
        modifier = modifier,
        verticalArrangement = spacedBy(16.dp)
    ){
        Text(
            text = "Regulamentos",
            style = Typography.headlineSmall,
            color = Gray400
        )

        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = rules.joinToString(separator = "\n", transform = { "• ${it.description}" }),
            style = Typography.labelMedium,
            lineHeight = 24.sp,
            color = Gray500
        )
    }
}

@Preview
@Composable
private fun MarketDetailsRulesPreview() {
    NearbyMarketDetailsRules(
        modifier = Modifier.fillMaxWidth(),
        rules = MockRules
    )
}