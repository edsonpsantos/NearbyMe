package pt.edsonsantos.nearbyme.ui.screen

sealed class MarketDetailsUIEvent {
    data class onFetchRules(val marketId: String): MarketDetailsUIEvent()
    data class onFetchCoupon(val qrCodeContent: String): MarketDetailsUIEvent()

    data object onResetCoupon: MarketDetailsUIEvent()


}