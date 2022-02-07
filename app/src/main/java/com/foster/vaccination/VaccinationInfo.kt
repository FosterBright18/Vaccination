package com.foster.vaccination;

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class VaccinationInfo(val country : String, val timeline : SortedMap<String, Int>): Parcelable
