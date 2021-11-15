package net.ambitious.android.autop2p.data.storage.firestore

import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.coroutines.flow.Flow

interface UsersStoreRepository {
  suspend fun setUserId(id: String)
  suspend fun addFireStore(usersStoreModel: UsersStoreModel, successListener: OnSuccessListener<Void>): Flow<Task<Void>>
  suspend fun getFireStore(successListener: OnSuccessListener<DocumentSnapshot>): Flow<Task<DocumentSnapshot>>

  companion object {
    const val COLLECTION_PATH = "user"
  }
}