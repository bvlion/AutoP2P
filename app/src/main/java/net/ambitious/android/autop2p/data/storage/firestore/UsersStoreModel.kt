package net.ambitious.android.autop2p.data.storage.firestore

import java.util.Date

data class UsersStoreModel(
  val ban: Boolean,
  val targetWiFiList: List<String>,
  val checkInterval: Long,
  val serverDeviceName: String,
  val latestUpdate: Date
) {
  companion object {
    const val WIFI_LIST_SEPARATOR = ","
  }
}

fun UsersStoreModel.toMap(): Map<String, *> {
  return hashMapOf(
    "ban" to this.ban,
    "targetWiFiList" to this.targetWiFiList.joinToString(UsersStoreModel.WIFI_LIST_SEPARATOR),
    "checkInterval" to this.checkInterval,
    "serverDeviceName" to this.serverDeviceName,
    "latestUpdate" to this.latestUpdate.time,
  )
}

fun Map<String, Any>.toUsersStore() = UsersStoreModel(
  this["ban"] as Boolean,
  (this["targetWiFiList"] as String).split(UsersStoreModel.WIFI_LIST_SEPARATOR),
  this["checkInterval"] as Long,
  this["serverDeviceName"] as String,
  Date(this["latestUpdate"] as Long)
)