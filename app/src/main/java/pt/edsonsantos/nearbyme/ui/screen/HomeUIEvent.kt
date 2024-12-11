package pt.edsonsantos.nearbyme.ui.screen

sealed class HomeUIEvent {
    data object onFetchCategories: HomeUIEvent()
    data class  onFetchMarkets(val categoryId: String): HomeUIEvent()
}