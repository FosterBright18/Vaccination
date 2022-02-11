package com.foster.vaccination

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.foster.vaccination.databinding.ActivityVaccinationListBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

public class VaccinationListActivity : AppCompatActivity() {


    private lateinit var binding : ActivityVaccinationListBinding
    lateinit var adapter : VaccinationAdapter
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
                adapter = VaccinationAdapter(vaccineList)
                binding.recyclerViewVaccinationList.adapter = adapter
                binding.recyclerViewVaccinationList.layoutManager = LinearLayoutManager(this@VaccinationListActivity)
            }

            override fun onFailure(call: Call<List<VaccinationInfo>>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })


        val worldwideCall = vaccineApi.getWorldwide()
        worldwideCall.enqueue(object: Callback<WorldwideInfo>{
            override fun onResponse(call: Call<WorldwideInfo>, response: Response<WorldwideInfo>) {
                Log.d(TAG, "onResponse: ${response.body()}")
            }

            override fun onFailure(call: Call<WorldwideInfo>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }
        })








    }

    override fun onCreateOptionsMenu(menu: Menu): kotlin.Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_list_sorting, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.menu_sorting_byName -> {
                Toast.makeText(this, "You sorted by name", Toast.LENGTH_SHORT).show()
                adapter.dataSet = adapter.dataSet.sortedBy {it.country}
                adapter.notifyDataSetChanged()
                true
            }
            R.id.menu_sorting_byTotalVax -> {
                Toast.makeText(this, "You sorted by total vaccinations", Toast.LENGTH_SHORT).show()
                adapter.dataSet = adapter.dataSet.sortedByDescending {it.timeline[it.timeline.lastKey()]}
                adapter.notifyDataSetChanged()
                true
            }
            R.id.menu_sorting_byDif -> {
                Toast.makeText(this, "You sorted by largest increase in the last 10 days", Toast.LENGTH_SHORT).show()
                adapter.dataSet = adapter.dataSet.sortedBy {
                    it.timeline[it.timeline.firstKey()]?.let { it1 ->
                        it.timeline[it.timeline.lastKey()]?.minus(
                            it1
                        )
                    }
                }
                adapter.notifyDataSetChanged()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }



}
