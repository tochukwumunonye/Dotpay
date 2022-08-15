package com.example.dotpay.data.remote.repository

import com.example.dotpay.data.remote.api.APIService
import com.example.dotpay.data.remote.makeupdto.MakeupDtoItem
import com.example.dotpay.data.remote.makeupdto.toMakeup
import com.example.dotpay.domain.model.Makeup
import com.example.dotpay.domain.repository.MakeupRepository
import javax.inject.Inject

class MakeupRepositoryImplementation @Inject constructor(
    private val api: APIService
): MakeupRepository{

    override suspend fun getMakeupItems(
        brandName: String,
        productType: String
    ): List<Makeup> {
      return  api.getMakeUpItems(brandName,productType).map { it.toMakeup() }
    }
}
