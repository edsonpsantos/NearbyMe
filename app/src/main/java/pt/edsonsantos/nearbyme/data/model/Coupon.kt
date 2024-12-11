package pt.edsonsantos.nearbyme.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Coupon(
    val coupon: String,
)