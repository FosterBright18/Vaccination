package com.foster.vaccination;

public class VaccinationListActivity {

    val vaccineApi = RetrofitHelper.getInstance().create(Covid19Service::class.java)
    val vaccineCall = vaccineApi.getVaccinations(10)

    vaccineCall.enueue(object : Callbacl<List<VaccinationInfo>> {

    }
    )

}
