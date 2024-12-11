package pt.edsonsantos.nearbyme.data.model


import kotlinx.serialization.Serializable

@Serializable
data class MarketDetails (
    val id: String,
    val categoryId:String,
    val name: String,
    var description: String,
    val coupon: Int,
    var rules: List<Rules> = emptyList(),
    var latitude: Double,
    var longitude: Double,
    val address: String,
    val phone: String,
    val cover: String,
)

