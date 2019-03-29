package retrofit.example.coinmarket

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun<T> Call<T>.enqueue(callback: CallbackKt<T>.() -> Unit) {
    val callBackKt = CallbackKt<T>()
    callback.invoke(callBackKt)
    this.enqueue(callBackKt)
}

class CallbackKt<T>: Callback<T> {
    var onResponse: ((Response<T>) -> Unit)? = null
    var onFailure: ((t: Throwable?) -> Unit)? = null

    override fun onFailure(call: Call<T>, t: Throwable) {
        onFailure?.invoke(t)
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        onResponse?.invoke(response)
    }
}