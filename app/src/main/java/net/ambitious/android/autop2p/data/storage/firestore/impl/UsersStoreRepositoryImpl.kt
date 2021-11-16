package net.ambitious.android.autop2p.data.storage.firestore.impl

import android.util.Log
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import net.ambitious.android.autop2p.data.storage.datastore.DataStoreManager
import net.ambitious.android.autop2p.data.storage.firestore.UsersStoreModel
import net.ambitious.android.autop2p.data.storage.firestore.UsersStoreRepository
import net.ambitious.android.autop2p.data.storage.firestore.toMap
import net.ambitious.android.autop2p.data.storage.firestore.toUsersStore
import javax.inject.Inject

class UsersStoreRepositoryImpl @Inject constructor(
  private val fireStore: FirebaseFirestore,
  private val dataStore: DataStoreManager
) : UsersStoreRepository {
  override suspend fun setUserId(id: String) {
    dataStore.setUserId(id)
  }

  override suspend fun addFireStore(usersStoreModel: UsersStoreModel, successListener: OnSuccessListener<Void>): Flow<Task<Void>> {
      return getDocument().map {
        it.set(usersStoreModel.toMap()).addOnFailureListener { e ->
          Log.e("autop2p","fire store error", e)
        }.addOnSuccessListener(successListener)
      }
  }

  override suspend fun getFireStore(successListener: OnSuccessListener<DocumentSnapshot>): Flow<Task<DocumentSnapshot>> {
    return getDocument().map { doc ->
      doc.get().addOnFailureListener { e ->
        Log.e("autop2p","fire get error", e)
      }.addOnSuccessListener(successListener)
    }
  }

  private fun getDocument(): Flow<DocumentReference> {
    return dataStore.userId.map {
      fireStore.collection(UsersStoreRepository.COLLECTION_PATH).document(it)
    }
  }
}