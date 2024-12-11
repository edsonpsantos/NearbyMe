package pt.edsonsantos.nearbyme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import pt.edsonsantos.nearbyme.data.model.Market
import pt.edsonsantos.nearbyme.ui.screen.HomeScreen
import pt.edsonsantos.nearbyme.ui.screen.HomeViewModel
import pt.edsonsantos.nearbyme.ui.screen.MarketDetailsScreen
import pt.edsonsantos.nearbyme.ui.screen.SplashScreen
import pt.edsonsantos.nearbyme.ui.screen.WelcomeScreen
import pt.edsonsantos.nearbyme.ui.screen.route.Home
import pt.edsonsantos.nearbyme.ui.screen.route.Splash
import pt.edsonsantos.nearbyme.ui.screen.route.Welcome
import pt.edsonsantos.nearbyme.ui.theme.NearbyMeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NearbyMeTheme {
                val navController = rememberNavController()
                val homeViewModel by viewModels<HomeViewModel>()
                val homeUIState by homeViewModel.uiState.collectAsStateWithLifecycle()

                NavHost(
                    navController = navController,
                    startDestination = Splash
                ) {
                    composable<Splash> {
                        SplashScreen(
                            onNavigateToWelcome = {
                                navController.navigate(Welcome)
                            }
                        )
                    }
                    composable<Welcome> {
                        WelcomeScreen(onNavigateToHome = {
                            navController.navigate(Home)
                        })
                    }

                    composable<Home> {
                        HomeScreen(
                            onNavigateToMarketDetails = { selectedMarket ->
                                navController.navigate(selectedMarket)
                            },
                            uiState = homeUIState,
                            onEvent = homeViewModel::onEvent
                        )
                    }

                    composable<Market> {
                        val selectedMarket = it.toRoute<Market>()
                        MarketDetailsScreen(
                            market = selectedMarket,
                            onNavigateBack = navController::popBackStack
                        )
                    }
                }
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NearbyMeTheme {
    }
}