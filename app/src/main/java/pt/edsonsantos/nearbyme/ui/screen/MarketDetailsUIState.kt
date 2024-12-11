package pt.edsonsantos.nearbyme.ui.screen

import pt.edsonsantos.nearbyme.data.model.Rules

data class MarketDetailsUIState(
    val rules: List<Rules>? = null,
    val coupon: String? = null,
)
