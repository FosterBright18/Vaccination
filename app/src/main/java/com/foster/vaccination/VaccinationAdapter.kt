package com.foster.vaccination

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class VaccinationAdapter(var dataSet: List<VaccinationInfo>) :
    RecyclerView.Adapter<VaccinationAdapter.ViewHolder>() {

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val textViewCountry : TextView
            val textViewVacCount : TextView
            val layout : ConstraintLayout

            init{
                textViewCountry = view.findViewById(R.id.textView_VaccinationItem_country)
                textViewVacCount = view.findViewById(R.id.textView_VaccinationItem_VacCount)
                layout = view.findViewById(R.id.layout_VaccinationItem)
            }
        }

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_vaccination, viewGroup, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            val country = dataSet[position]
            viewHolder.textViewCountry.text = country.country
            viewHolder.textViewVacCount.text = country.timeline[country.timeline.lastKey()].toString()
                val context = viewHolder.layout.context
                val heroDetailIntent = Intent(context, VaccinationDetailActivity::class.java).apply{
                    putExtra(VaccinationDetailActivity.EXTRA_COUNTRY, country)
                }
                context.startActivity(heroDetailIntent)
            }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}

