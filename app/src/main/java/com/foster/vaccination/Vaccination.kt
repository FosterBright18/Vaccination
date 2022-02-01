package com.foster.vaccination;

import android.os.Parcelable
import java.util.*
import kotlinx.android.parcel.Parcelize as Parcelize1

@Parcelize1
public class Vaccination (
    val country: String,
    val timeline: SortedMap<String, Long>
) : Parcelize1(), Parcelable