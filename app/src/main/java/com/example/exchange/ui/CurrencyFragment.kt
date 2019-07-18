package com.example.exchange.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exchange.R

import com.example.exchange.provideCurrencyViewModelFactory
import kotlinx.android.synthetic.main.fragment_currency.*

/**
 *
 * Created by Marioara Rus on 2019-07-17
 */
class CurrencyFragment : Fragment(), OnItemClicked {

    private lateinit var currencyAdapter : CurrencyAdapter
    private val currencyViewModel: CurrencyViewModel by lazy {
        ViewModelProviders.of(this, provideCurrencyViewModelFactory())
            .get(CurrencyViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_currency, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        currencyAdapter = CurrencyAdapter(view.context)
        currencyViewModel.currencyRates.observe(viewLifecycleOwner, Observer {
            tvEmpty.visibility = View.GONE
            rvCurrency.visibility = View.VISIBLE
            currencyAdapter.setCurrencies(it)
        })

        rvCurrency.layoutManager = LinearLayoutManager(context)
        rvCurrency.setHasFixedSize(true)
        rvCurrency.setItemViewCacheSize(40)
        rvCurrency.adapter = currencyAdapter
    }
    override fun onClick(currencyAmount: Float) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}