package pt.edsonsantos.nearbyme.ui.screen

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
            NearbyMeRemoteDataSource.patchCoupon(marketId = qrCodeContent)
                .onSuccess { coupon ->
                    _uiState.update { currentUiState ->
                        currentUiState.copy(coupon = coupon.coupon)
                    }
                }
                .onFailure {
                    _uiState.update { currentUiState ->
                        currentUiState.copy(coupon = null)
                    }
                }
        }
    }

    private fun fetchRules(marketId: String) {
        viewModelScope.launch {
            NearbyMeRemoteDataSource.getMarketDetails(marketId = marketId)
                .onSuccess { marketDetail ->
                    _uiState.update { currentUiState ->
                        currentUiState.copy(rules = marketDetail.rules)
                    }
                }
                .onFailure {
                    _uiState.update { currentUiState ->
                        currentUiState.copy(rules = null)
                    }
                }
        }
    }

    private  fun resetCoupon(){
        _uiState.update { currentUiState ->
            currentUiState.copy(coupon = null)
        }
    }
}