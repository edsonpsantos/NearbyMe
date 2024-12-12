package pt.edsonsantos.nearbyme.ui.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay
import pt.edsonsantos.nearbyme.R
import pt.edsonsantos.nearbyme.ui.theme.GreenLight

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    onNavigateToWelcome: () -> Unit
) {

    LaunchedEffect(key1= Unit) {
        delay(3000)
        onNavigateToWelcome()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(GreenLight)
    ){
        Image(
            modifier = Modifier
                .align(Alignment.Center),
            painter = painterResource(id = R.drawable.img_logo_logo_logo_text),
            contentDescription = "Image de Logo"
        )

        Image(
            modifier = Modifier.align(Alignment.BottomCenter),
            painter = painterResource(id = R.drawable.bg_splash_screen),
            contentDescription = "Image de Background"
        )
    }
}

@Preview
@Composable
private fun SplashScreenPreview() {
    SplashScreen(onNavigateToWelcome = {})
}