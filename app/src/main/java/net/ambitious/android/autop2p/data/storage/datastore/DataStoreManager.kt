package net.ambitious.android.autop2p.data.storage.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreManager(context: Context) {
  private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("settings")

  private val settingsDataStore = context.dataStore

  suspend fun setUserId(id: String) {
    settingsDataStore.edit {
      it[USER_ID_KEY] = id
    }
  }

  val userId: Flow<String> = settingsDataStore.data.map {
    it[USER_ID_KEY] ?: ""
  }

  companion object {
    private val USER_ID_KEY = stringPreferencesKey("user_id_key")
  }
}