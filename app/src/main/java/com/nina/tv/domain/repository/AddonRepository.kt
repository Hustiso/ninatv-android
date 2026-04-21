package com.nina.tv.domain.repository

import com.nina.tv.core.network.NetworkResult
import com.nina.tv.domain.model.Addon
import kotlinx.coroutines.flow.Flow

interface AddonRepository {
    fun getInstalledAddons(): Flow<List<Addon>>
    suspend fun fetchAddon(baseUrl: String): NetworkResult<Addon>
    suspend fun addAddon(url: String)
    suspend fun removeAddon(url: String)
    suspend fun setAddonOrder(urls: List<String>)
}
