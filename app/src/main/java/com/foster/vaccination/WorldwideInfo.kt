package com.foster.vaccination;

import android.os.Parcelable;
import kotlinx.parcelize.Parcelize;
import java.util.*;

@Parcelize
data class WorldwideInfo(val cases : Long, val deaths : Long, val population : Long): Parcelable
