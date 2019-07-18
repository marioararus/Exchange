package com.example.exchange.data.entity

import com.example.exchange.R

/**
 *
 * Created by Marioara Rus on 2019-07-17
 */
enum class Currencies(val currencyCode: String, val currencyName: String, val flagResId: Int) {

    AUD("AUD", "Australian Dollar", R.drawable.flag_square_australia),
    BGN("BGN", "Bulgarian Lev", R.drawable.flag_square_bulgaria),
    BRL("BRL", "Brazilian Real", R.drawable.flag_square_brazil),
    CAD("CAD", "Canadian Dollar", R.drawable.flag_square_canada),
    CHF("CHF", "Swiss Franc", R.drawable.flag_square_switzerland),
    CNY("CNY", "Chinese Yuan", R.drawable.flag_square_china),
    CZK("CZK", "Czech Republic Koruna", R.drawable.flag_square_czech_republic),
    DKK("DKK", "Danish Krone", R.drawable.flag_square_denmark),
    EUR("EUR", "European Euro", R.drawable.flag_square_european_union),
    GBP("GBP", "British Pound", R.drawable.flag_square_united_kingdom),
    HKD("HKD", "Hong Kong Dollar", R.drawable.flag_square_hong_kong),
    HRK("HRK", "Croatian Kuna", R.drawable.flag_square_croatia),
    HUF("HUF", "Hungarian Forint", R.drawable.flag_square_hungary),
    IDR("IDR", "Indonesian Rupiah", R.drawable.flag_square_indonesia),
    ILS("ILS", "Israeli New Sheqel", R.drawable.flag_square_israel),
    INR("INR", "Indian Rupee", R.drawable.flag_square_india),
    ISK("ISK", "Icelandic Króna", R.drawable.flag_square_iceland),
    JPY("JPY", "Japanese Yen", R.drawable.flag_square_japan),
    KRW("KRW", "South Korean Won", R.drawable.flag_square_south_korea),
    MXN("MXN", "Mexican Peso", R.drawable.flag_square_mexico),
    MYR("MYR", "Malaysian Ringgit", R.drawable.flag_square_malaysia),
    NOK("NOK", "Norwegian Krone", R.drawable.flag_square_norway),
    NZD("NZD", "New Zealand Dollar", R.drawable.flag_square_new_zealand),
    PHP("PHP", "Philippine Peso", R.drawable.flag_square_philippines),
    PLN("PLN", "Polish Zloty", R.drawable.flag_square_poland),
    RON("RON", "Romanian Leu", R.drawable.flag_square_romania),
    RUB("RUB", "Russian Ruble", R.drawable.flag_square_russia),
    SEK("SEK", "Swedish Krona", R.drawable.flag_square_sweden),
    SGD("SGD", "Singapore Dollar", R.drawable.flag_square_singapore),
    THB("THB", "Thai Baht", R.drawable.flag_square_thailand),
    TRY("TRY", "Turkish Lira", R.drawable.flag_square_turkey),
    USD("USD", "US Dollar", R.drawable.flag_square_united_states_of_america),
    ZAR("ZAR", "South African Rand", R.drawable.flag_square_south_africa);
    companion object {

        fun getCurrency(code: String): Currencies? {
            return values().find { it.currencyCode == code }

        }
    }
}