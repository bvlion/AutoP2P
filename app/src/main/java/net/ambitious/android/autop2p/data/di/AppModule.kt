package net.ambitious.android.autop2p.data.di

import android.content.Context
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.ambitious.android.autop2p.data.storage.datastore.DataStoreManager
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

  @Provides
  @Singleton
  fun provideDataStore(@ApplicationContext context: Context) = DataStoreManager(context)

  @Provides
  @Singleton
  fun provideFireStore() = FirebaseFirestore.getInstance()
}
