package com.nina.tv.core.di

import com.nina.tv.data.repository.AddonRepositoryImpl
import com.nina.tv.data.repository.CatalogRepositoryImpl
import com.nina.tv.data.repository.LibraryRepositoryImpl
import com.nina.tv.data.repository.MetaRepositoryImpl
import com.nina.tv.data.repository.StreamRepositoryImpl
import com.nina.tv.data.repository.SubtitleRepositoryImpl
import com.nina.tv.data.repository.SyncRepositoryImpl
import com.nina.tv.data.repository.WatchProgressRepositoryImpl
import com.nina.tv.domain.repository.AddonRepository
import com.nina.tv.domain.repository.CatalogRepository
import com.nina.tv.domain.repository.LibraryRepository
import com.nina.tv.domain.repository.MetaRepository
import com.nina.tv.domain.repository.StreamRepository
import com.nina.tv.domain.repository.SubtitleRepository
import com.nina.tv.domain.repository.SyncRepository
import com.nina.tv.domain.repository.WatchProgressRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAddonRepository(impl: AddonRepositoryImpl): AddonRepository

    @Binds
    @Singleton
    abstract fun bindCatalogRepository(impl: CatalogRepositoryImpl): CatalogRepository

    @Binds
    @Singleton
    abstract fun bindLibraryRepository(impl: LibraryRepositoryImpl): LibraryRepository

    @Binds
    @Singleton
    abstract fun bindMetaRepository(impl: MetaRepositoryImpl): MetaRepository

    @Binds
    @Singleton
    abstract fun bindStreamRepository(impl: StreamRepositoryImpl): StreamRepository

    @Binds
    @Singleton
    abstract fun bindSubtitleRepository(impl: SubtitleRepositoryImpl): SubtitleRepository

    @Binds
    @Singleton
    abstract fun bindSyncRepository(impl: SyncRepositoryImpl): SyncRepository

    @Binds
    @Singleton
    abstract fun bindWatchProgressRepository(impl: WatchProgressRepositoryImpl): WatchProgressRepository
}
