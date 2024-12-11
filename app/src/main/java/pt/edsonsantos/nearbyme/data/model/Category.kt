package pt.edsonsantos.nearbyme.data.model


import androidx.annotation.DrawableRes
import kotlinx.serialization.Serializable
import pt.edsonsantos.nearbyme.ui.components.category.CategoryFilterChipView

@Serializable
data class Category(
    val id: String,
    val name: String
){
    @get:DrawableRes
    val icon: Int?
        get() = CategoryFilterChipView.fromDescription(description=name)?.icon
}
