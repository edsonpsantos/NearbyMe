package pt.edsonsantos.nearbyme.data.model


import androidx.annotation.DrawableRes
import pt.edsonsantos.nearbyme.R

enum class CategoryFilterChipView(
    val description: String,
    @DrawableRes val icon: Int,
) {
    ALIMENTAR(description = "Alimentação", icon = R.drawable.ic_tools_kitchen_2),
    COMPRAR(description = "Compras", icon = R.drawable.ic_shopping_bag),
    HOSPEDAGEM(description = "Hospedagem", icon = R.drawable.ic_bed),
    SUPERMERCADO(description = "Supermercado", icon = R.drawable.ic_shopping_cart),
    ENTRETENIMENTO(description = "Cinema", icon = R.drawable.ic_movie),
    FARMACIA(description = "Farmácia", icon = R.drawable.ic_first_aid_kit),
    COMBUSTIVEL(description = "Combustível", icon = R.drawable.ic_gas_station),
    PADARIA(description = "Padaria", icon = R.drawable.ic_bakery);

    companion object {
        fun fromDescription(description: String): CategoryFilterChipView? {
            return entries.find { it.description == description }
        }
    }
}