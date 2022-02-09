package com.foster.vaccination;

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.foster.vaccination.databinding.ActivityVaccinationDetailBinding

class VaccinationDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVaccinationDetailBinding

    companion object {
        val EXTRA_COUNTRY = "country"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVaccinationDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val countryInfo = intent.getParcelableExtra<VaccinationInfo>(EXTRA_COUNTRY)
        binding.textViewDetailCountry.text = countryInfo?.country ?: "country"
        binding.textViewDetailTimeline.text = ((countryInfo?.timeline ?: "timeline").toString())
        }
    }
