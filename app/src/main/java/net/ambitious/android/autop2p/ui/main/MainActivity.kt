package net.ambitious.android.autop2p.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import net.ambitious.android.autop2p.ui.theme.AutoP2PTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  private val viewModel by viewModels<MainViewModel>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)


    setContent {
      AutoP2PTheme {
        viewModel.setUser()
        viewModel.stored.observe(this) {
          if (it) {
            viewModel.getUser()
          }
        }
        val usersStore by viewModel.usersStore.observeAsState()
        Surface(color = MaterialTheme.colors.background) {
          Greeting(usersStore?.serverDeviceName ?: "null ??")
        }
      }
    }
  }
}

@Composable
fun Greeting(name: String) {
  Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  AutoP2PTheme {
    Greeting("Android")
  }
}