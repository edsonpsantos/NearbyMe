package pt.edsonsantos.nearbyme.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pt.edsonsantos.nearbyme.core.network.NearbyMeRemoteDataSource

class HomeViewModel: ViewModel()  {
    private val _uiState = MutableStateFlow(HomeUIState())
    val uiState: StateFlow<HomeUIState> = _uiState.asStateFlow()

    fun onEvent(event: HomeUIEvent){
        when(event){
            is HomeUIEvent.onFetchCategories -> fetchCategories()
            is HomeUIEvent.onFetchMarkets -> fetchMarkets(categoryId = event.categoryId)
        }

    }

    private fun fetchCategories(){
        viewModelScope.launch {
            _uiState.update { currentUiState ->
                NearbyMeRemoteDataSource.getCategories().fold(
                    onSuccess = { categories ->
                        currentUiState.copy(categories = categories)
                    },
                    onFailure = { _ ->
                        currentUiState.copy(categories = emptyList())
                    }
                )
            }
        }
    }

    private fun fetchMarkets(categoryId: String){
        viewModelScope.launch {
            _uiState.update { currentUiState ->
                NearbyMeRemoteDataSource.getMarkets(categoryId = categoryId).fold(
                    onSuccess = { markets ->
                        currentUiState.copy(
                            markets = markets,
                            marketLocation = markets.map { market ->
                                LatLng(market.latitude, market.longitude)
                            }
                        )
                    },
                    onFailure = { _ ->
                        currentUiState.copy(
                            markets = emptyList(),
                            marketLocation = emptyList()
                        )
                    }
                )
            }
        }
    }
}