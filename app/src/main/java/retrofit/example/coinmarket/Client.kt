package retrofit.example.coinmarket

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Client {
   // val BASE_URL = "https://httpbin.org/"
    val BASE_URL = "https://3.223.234.9/"
    var retrofit: Retrofit? = null
    val okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient()
    fun getClient(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }
}