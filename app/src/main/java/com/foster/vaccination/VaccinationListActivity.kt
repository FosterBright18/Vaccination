package com.foster.vaccination

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.foster.vaccination.databinding.ActivityVaccinationListBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

public class VaccinationListActivity : AppCompatActivity() {


    private lateinit var binding : ActivityVaccinationListBinding
    val TAG = "VaccinationListActivity"

    override fun onCreate(savedInstanseState: Bundle?) {
        super.onCreate(savedInstanseState)
        binding = ActivityVaccinationListBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var vaccineList = listOf<VaccinationInfo>()
//        vaccineList.add(VaccinationInfo("Fake 1",
//            sortedMapOf<String, Int>(
//                Pair("1/23/22", 100),
//                Pair("1/24/22", 105),
//                Pair("1/25/22", 110)
//            )
//        ))
//        vaccineList.add(VaccinationInfo("Fake 2",
//            sortedMapOf<String, Int>(
//                Pair("1/23/22", 50000),
//                Pair("1/24/22", 60000),
//                Pair("1/25/22", 70000)
//            )
//        ))
//

//
//
//        val country1 = vaccineList[0]
//        val firstDay = country1.timeline.firstKey()
//        val lastDay = country1.timeline.lastKey()
//        country1.timeline.get(firstDay)
//
//        country1.timeline.toList().sortedBy {
//            it.second
//        }[0]



        val vaccineApi = RetrofitHelper.getInstance().create(Covid19Service::class.java)
        val vaccineCall = vaccineApi.getVaccinations(10)

        vaccineCall.enqueue(object : Callback<List<VaccinationInfo>> {
            override fun onResponse(
                call: Call<List<VaccinationInfo>>,
                response: Response<List<VaccinationInfo>>
            ) {
                Log.d(TAG, "onResponse: ${response.body()}")
                vaccineList = response.body() ?: listOf<VaccinationInfo>()
                val adapter = VaccinationAdapter(vaccineList)
                binding.recyclerViewVaccinationList.adapter = adapter
                binding.recyclerViewVaccinationList.layoutManager = LinearLayoutManager(this@VaccinationListActivity)
            }

            override fun onFailure(call: Call<List<VaccinationInfo>>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })



    }





}
