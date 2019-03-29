package retrofit.example.coinmarket

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_layout.view.*
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
//import org.junit.experimental.results.ResultMatchers.isSuccessful



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = CoinAdapter{
            /*val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra(COIN_DETAILS_KEY, coinProperty)
            startActivity(intent)*/
        }
        getCoins()
    }
    private val coinsAdapter get() = recycler_view.adapter as CoinAdapter


    private fun getCoins() {
        val apiService = Client.getClient().create(ApiService::class.java)
        val call = apiService.getCoins(50)
        call.enqueue{
            onResponse = {
                if(it.isSuccessful){
                    progress_bar.visibility = View.GONE
                    coinsAdapter.updateCoins(it.body()!!)
                }
            }

            onFailure = {
                progress_bar.visibility = View.GONE
                Toast.makeText(this@MainActivity, "Unable to proceed", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    internal inner class CoinViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(listener: (Coin) -> Unit, coinProperty: Coin) {
            itemView.setOnClickListener{
                listener(coinProperty)
            }

            itemView.text_view_coin.text = coinProperty.id
            itemView.text_view_rank.text = coinProperty.rank
        }
    }

    private inner class CoinAdapter(val onclickListener: (Coin) -> Unit) : RecyclerView.Adapter<CoinViewHolder>() {
        private var mCoins:MutableList<Coin> = arrayListOf()

        fun updateCoins(newCoins:List<Coin>){
            mCoins.clear()
            mCoins.addAll(newCoins)
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): CoinViewHolder {
            return CoinViewHolder(LayoutInflater.from(this@MainActivity).inflate(R.layout.item_layout, viewGroup, false))
        }

        override fun onBindViewHolder(holder: CoinViewHolder, position: Int) = holder.bind( onclickListener, mCoins[position])

        override fun getItemCount(): Int {
            return mCoins.size
        }
    }
}
