package com.example.exchange.ui

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.exchange.R
import com.example.exchange.data.entity.CurrencyRate
import com.example.exchange.provideCurrencyViewModelFactory
import kotlinx.android.synthetic.main.fragment_currency.*

/**
 *
 * Created by Marioara Rus on 2019-07-17
 */
class CurrencyFragment : Fragment(), OnItemClicked {

    private lateinit var currencyAdapter: CurrencyAdapter
    private val currencyViewModel: CurrencyViewModel by lazy {
        ViewModelProviders.of(this, provideCurrencyViewModelFactory("EUR"))
            .get(CurrencyViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_currency, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        currencyAdapter = CurrencyAdapter(view.context)
        currencyAdapter.onItemClicked = this

        currencyViewModel.getCurrencies()

        context?.let { context ->
            setImage(context, ivCurrency, R.drawable.flag_square_european_union)
        }

        currencyViewModel.currency.observe(viewLifecycleOwner, Observer {
            tvRate.text = it.code
            tvFullNameRate.text = it.name
        })

        currencyViewModel.currencyRates.observe(viewLifecycleOwner, Observer {
            tvEmpty.visibility = View.GONE
            rvCurrency.visibility = View.VISIBLE
            currencyAdapter.setCurrencies(it.toMutableList())
        })

        rvCurrency.layoutManager = LinearLayoutManager(context)
        rvCurrency.setHasFixedSize(true)
        rvCurrency.setItemViewCacheSize(40)
        rvCurrency.adapter = currencyAdapter

        var editableNumber = 1F
        currencyAdapter.setEditableNumber(editableNumber)

        etAmount.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s?.length != 0) {
                    editableNumber = s.toString().toFloat()
                    currencyAdapter.setEditableNumber(editableNumber)
                } else {
                    editableNumber = 0F
                    currencyAdapter.setEditableNumber(editableNumber)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.length != 0) {
                    editableNumber = s.toString().toFloat()
                    currencyAdapter.setEditableNumber(editableNumber)
                } else {
                    editableNumber = 0F
                    currencyAdapter.setEditableNumber(editableNumber)
                }
            }
        })


    }

    override fun onClick(currencyRate: CurrencyRate) {
        currencyViewModel.currencyCode = currencyRate.code!!
        tvRate.text = currencyRate.code
        tvFullNameRate.text = currencyRate.name
        etAmount.text.clear()

        context?.let { context ->
            currencyRate.iconResId?.let { iconResId ->
                setImage(context, ivCurrency, iconResId)
            }
        }
    }

    private fun setImage(context: Context, imageView: ImageView, iconResId: Int) {
        val requestOptions = RequestOptions()
            .error(R.drawable.flag_square_european_union)

        context?.let { context ->
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
                .into(imageView)
        }
    }
}