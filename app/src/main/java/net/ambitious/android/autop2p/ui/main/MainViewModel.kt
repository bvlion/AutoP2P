package net.ambitious.android.autop2p.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import net.ambitious.android.autop2p.data.storage.firestore.UsersStoreModel
import net.ambitious.android.autop2p.data.storage.firestore.UsersStoreRepository
import net.ambitious.android.autop2p.data.storage.firestore.toUsersStore
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
  private val usersStoreRepository: UsersStoreRepository
) : ViewModel() {

  private val _usersStore = MutableLiveData<UsersStoreModel?>()
  val usersStore get() = _usersStore

  private val _stored = MutableLiveData<Boolean>()
  val stored get() = _stored

  fun setUser() {
    viewModelScope.launch {
      usersStoreRepository.setUserId("bvlion")
      usersStoreRepository.addFireStore(
        UsersStoreModel(
          false,
          listOf("test", "test2"),
          500,
          "server",
          Date()
        )
      ) {
        _stored.postValue(true)
      }.collect {
        _stored.postValue(false)
      }
    }
  }

  fun getUser() {
    viewModelScope.launch {
      usersStoreRepository.getFireStore {
        _usersStore.postValue(it.data?.toUsersStore())
      }.collect {
        _usersStore.postValue(null)
      }
    }
  }
}