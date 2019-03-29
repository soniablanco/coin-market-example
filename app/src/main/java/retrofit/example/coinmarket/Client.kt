package retrofit.example.coinmarket

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Client {
    val BASE_URL = "https://api.coinmarketcap.com/v1/"
    var retrofit: Retrofit? = null

    fun getClient(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }
}