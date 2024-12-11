package pt.edsonsantos.nearbyme.ui.screen

import com.google.android.gms.maps.model.LatLng
import pt.edsonsantos.nearbyme.data.model.Category
import pt.edsonsantos.nearbyme.data.model.Market

data class HomeUIState(
    val categories: List<Category>? = null,
    val markets: List<Market>? = null,
    val marketLocation: List<LatLng>? = null,

    )
