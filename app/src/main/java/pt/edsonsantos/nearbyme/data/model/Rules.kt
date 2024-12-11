package pt.edsonsantos.nearbyme.data.model


import kotlinx.serialization.Serializable

@Serializable
data class Rules(
    val id:String,
    val description: String,
    val marketId: String
)