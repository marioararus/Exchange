package com.example.exchange.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.exchange.R
import com.example.exchange.data.entity.CurrencyRate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_currency.*

/**
 *
 * Created by Marioara Rus on 2019-07-11
 */
internal class CurrencyAdapter(val context: Context) :
    RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>() {

    private var editableNumber: Float = 0F
    private var currencyRates: MutableList<CurrencyRate> = mutableListOf()
    fun setCurrencies(currencyRates: MutableList<CurrencyRate>) {
        this.currencyRates = currencyRates
        notifyDataSetChanged()
    }

    var onItemClicked: OnItemClicked? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        return CurrencyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_currency, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bindCurrency(currencyRates[position])

        holder.itemView.setOnClickListener { itemView ->
            holder.currencyRate.let { currencyRate ->
                onItemClicked?.onClick(currencyRate)
            }
        }
    }

    override fun getItemCount() = currencyRates.size

    internal inner class CurrencyViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        lateinit var currencyRate: CurrencyRate
        fun bindCurrency(currencyRate: CurrencyRate) {
            this.currencyRate = currencyRate
            currencyRate.run {
                tvRate.text = code
                tvFullNameRate.text = name
                tvAmount.text = multiplyWithCurrencyToString(editableNumber, amount)

                val requestOptions = RequestOptions()
                    .error(R.drawable.flag_square_european_union)

                Glide.with(context)
                    .load(iconResId)
                    .thumbnail(0.1F)
                    .apply(requestOptions)
                    .error(
                        Glide.with(context)
                            .load(iconResId)
                            .thumbnail(0.1F)
                            .apply(requestOptions)
                    )
                    .into(ivCurrency)

            }
        }
    }

    fun setEditableNumber(currencyNumber: Float) {
        this.editableNumber = currencyNumber
        notifyDataSetChanged()
    }

    fun multiplyWithCurrencyToString(currencyNumber: Float, amount: Float?): String {
        var finalAmount = 0F
        amount?.let {
            finalAmount = if (amount != 0F && currencyNumber != 0F) {
                currencyNumber * amount
            } else if (currencyNumber == 0F) {
                currencyNumber
            } else {
                amount
            }
        }
        return finalAmount.toString()
    }
}

interface OnItemClicked {
    fun onClick(currencyRate: CurrencyRate)
}