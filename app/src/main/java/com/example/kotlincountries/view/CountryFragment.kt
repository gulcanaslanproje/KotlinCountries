package com.example.kotlincountries.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.kotlincountries.R
import com.example.kotlincountries.Utils.downloadFromUrl
import com.example.kotlincountries.Utils.placeholderProgressBar
import com.example.kotlincountries.databinding.FragmentCountryBinding
import com.example.kotlincountries.viewmodel.CountryViewModel
import com.example.kotlincountries.viewmodel.FeedViewModel
import kotlinx.android.synthetic.main.fragment_country.*
import kotlinx.android.synthetic.main.fragment_feed.*
import kotlinx.android.synthetic.main.item_country.view.*

class CountryFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var viewModel: CountryViewModel
    private var countryUuid = 0
    private lateinit var dataBinding: FragmentCountryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_country, container, false)
        return dataBinding.root
        //return inflater.inflate(R.layout.fragment_country, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            countryUuid = CountryFragmentArgs.fromBundle(it).countryUuid
        }
        viewModel = ViewModelProviders.of(this).get(CountryViewModel::class.java)
        viewModel.getDataFromRoom(countryUuid)


        obserLiveData()

    }

    private fun obserLiveData() {

        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer { country ->
            country?.let {
                dataBinding.selectedCountry = country

                /* countryName.text = country.countryName
                 countryCapital.text = country.countryCapital
                 countryRegion.text = country.countryRegion
                 countryCurrency.text = country.countryCurrency
                 countryLanguage.text = country.countryLanguage

                 context?.let {
                     countryImage.downloadFromUrl(
                         country.imageUrl,
                         placeholderProgressBar(it)
                     )
                 }*/

            }
        })

    }

}