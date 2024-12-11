package pt.edsonsantos.nearbyme.core.network

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.patch
import pt.edsonsantos.nearbyme.core.network.KtorHttpClient.httpClientAndroid
import pt.edsonsantos.nearbyme.data.model.Category
import pt.edsonsantos.nearbyme.data.model.Coupon
import pt.edsonsantos.nearbyme.data.model.Market
import pt.edsonsantos.nearbyme.data.model.MarketDetails

object NearbyMeRemoteDataSource {

    private const val LOCALHOST_EMULATOR_BASE_URL = "http://10.0.2.2:3333"
    private const val BASE_URL = LOCALHOST_EMULATOR_BASE_URL

    // HttpGet Buscar categorias
    // HttpGet Buscar Locais por categoryID
    // HttpGet Buscar Detalhes do Local por localID
    // HttpPostGerar cupom por QRCODE

    suspend fun getCategories(): Result<List<Category>> = try {

        val categories = httpClientAndroid.get("$BASE_URL/categories").body<List<Category>>()

        Result.success(categories)

    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun getMarkets(categoryId: String): Result<List<Market>> = try {

        val markets =
            httpClientAndroid.get("$BASE_URL/markets/category/${categoryId}").body<List<Market>>()

        Result.success(markets)

    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun getMarketDetails(marketId: String): Result<MarketDetails> = try {

        val market =
            httpClientAndroid.get("$BASE_URL/markets/${marketId}").body<MarketDetails>()

        Result.success(market)

    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun patchCoupon(marketId: String): Result<Coupon> = try {
        val coupon = httpClientAndroid.patch("$BASE_URL/coupons/${marketId}").body<Coupon>()

        Result.success(coupon)

    } catch (e:Exception){
        Result.failure(e)
    }
}