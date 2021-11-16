package net.ambitious.android.autop2p.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import net.ambitious.android.autop2p.data.storage.firestore.UsersStoreRepository
import net.ambitious.android.autop2p.data.storage.firestore.impl.UsersStoreRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

  @Binds
  @Singleton
  abstract fun provideUsersStoreRepository(usersStoreRepositoryImpl: UsersStoreRepositoryImpl): UsersStoreRepository
}