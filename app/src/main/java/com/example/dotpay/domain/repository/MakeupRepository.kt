package com.example.dotpay.domain.repository

import com.example.dotpay.data.remote.makeupdto.MakeupDtoItem
import com.example.dotpay.domain.model.Makeup

interface MakeupRepository {

    suspend fun getMakeupItems(brandName: String, productType: String): List<Makeup>
}
