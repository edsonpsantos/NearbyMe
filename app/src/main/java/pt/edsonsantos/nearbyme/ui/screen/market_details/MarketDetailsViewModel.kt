package pt.edsonsantos.nearbyme.ui.screen.market_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pt.edsonsantos.nearbyme.core.network.NearbyMeRemoteDataSource

class MarketDetailsViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MarketDetailsUIState())
    val uiState: StateFlow<MarketDetailsUIState> = _uiState.asStateFlow()

    fun onEvent(event: MarketDetailsUIEvent) {
        when (event) {
            is MarketDetailsUIEvent.onFetchCoupon -> fetchCoupon(qrCodeContent = event.qrCodeContent)
            is MarketDetailsUIEvent.onFetchRules -> fetchRules(marketId = event.marketId)
            MarketDetailsUIEvent.onResetCoupon -> resetCoupon()
        }
    }

    private fun fetchCoupon(qrCodeContent: String) {
        viewModelScope.launch {
            NearbyMeRemoteDataSource.patchCoupon(marketId = qrCodeContent).fold(
                onSuccess = { coupon ->
                    _uiState.update { currentUiState ->
                        currentUiState.copy(coupon = coupon.coupon)
                    }
                },
                onFailure = {
                    _uiState.update { currentUiState ->
                        currentUiState.copy(coupon = "")
                    }
                }
            )
        }
    }

    private fun fetchRules(marketId: String) {
        viewModelScope.launch {
            NearbyMeRemoteDataSource.getMarketDetails(marketId = marketId).fold(
                onSuccess = { marketDetails ->
                    _uiState.update { currentUiState ->
                        currentUiState.copy(rules = marketDetails.rules)
                    }
                },
                onFailure = {
                    _uiState.update { currentUiState ->
                        currentUiState.copy(rules = emptyList())
                    }
                }
            )
        }
    }

    private  fun resetCoupon(){
        _uiState.update { currentUiState ->
            currentUiState.copy(coupon = null)
        }
    }
}