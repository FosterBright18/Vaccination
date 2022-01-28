package com.foster.vaccination;

class VaccinationDetailActivity : AppCompactActivity() {

    companion pbject {
        val EXTRA_COUNTRY = "country"
        }

    private lateinit var binding: ActivityVaccinationDetailBinding
    occeride fun onCreate(savedInstanceState: Bundle?) {
        super.oncreate(savedInstanceState)
        binding = ActivityVaccinationDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val countryInfo = intent.getParcelableExtra<VaccinationInfo>(EXTRA_COUNTRY)
        binding.textViewVaxDetailCountry.text = countryInfo?,country
        binding.textViewVaxDetailLatestVax.text =
            countryInfo?,timeline?,toList()?.joinToString {
            it.first + ": " + it.second + "\n"
        }
        }
}
