package pt.edsonsantos.nearbyme.ui.components.category

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pt.edsonsantos.nearbyme.data.model.Category
import pt.edsonsantos.nearbyme.ui.theme.Gray300
import pt.edsonsantos.nearbyme.ui.theme.Gray400
import pt.edsonsantos.nearbyme.ui.theme.GreenBase
import pt.edsonsantos.nearbyme.ui.theme.Typography


@Composable
fun NearbyCategoryFilterChip(
    modifier: Modifier = Modifier,
    category: Category,
    isSelected: Boolean,
    onClick: (isSelected: Boolean) -> Unit
) {
    FilterChip(modifier = modifier
        .padding(2.dp)
        .heightIn(min = 36.dp),
                elevation = FilterChipDefaults.filterChipElevation(elevation = 8.dp),
                leadingIcon = {
                    category.icon?.let {
                        Icon(
                            modifier = Modifier
                                .padding(end = 16.dp)
                                .size(20.dp),
                            painter = painterResource(id = it),
                            contentDescription = "Icone de filtro de categoria",
                            tint = if (isSelected) Color.White else Gray400,
                        )
                    }
                },
                border = FilterChipDefaults.filterChipBorder(
                    enabled = false,
                    selected = isSelected,
                    disabledBorderColor = Gray300,
                    borderWidth = 1.dp,
                    selectedBorderWidth = 0.dp,
                    selectedBorderColor = Color.Transparent,
                ),
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = Color.White,
                    selectedContainerColor = GreenBase
                ) ,
                selected = isSelected,
                label = {
                    Text(
                        text = category.name,
                        style = Typography.bodyMedium,
                        color = if (isSelected) Color.White else Gray400
                    )
                },
                onClick = { onClick(!isSelected) },
        )
}

@Preview
@Composable
private fun NearbyCategoryFilterChipSelectedPreview() {
    NearbyCategoryFilterChip(
        category = Category(id = "1", name = "Alimentação"),
        isSelected = true,
        onClick = {}
    )
}

@Preview
@Composable
private fun NearbyCategoryFilterChipNotSelectedPreview() {
    NearbyCategoryFilterChip(
        category = Category(id = "1", name = "Padaria"),
        isSelected = false,
        onClick = {}
    )
}