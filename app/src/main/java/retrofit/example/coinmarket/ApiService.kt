package retrofit.example.coinmarket

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("ticker")
    fun getCoins(@Query("limit") limit: Int): Call<List<Coin>>
    @GET("get")
    fun getResponse(): Call<ResponseTest>
}