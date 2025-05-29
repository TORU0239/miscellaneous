package kr.toru.miscellaneous

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kr.toru.miscellaneous.core.network.model.request.NameRqDTO
import kr.toru.miscellaneous.core.network.service.ApiService
import kr.toru.miscellaneous.ui.theme.MiscellaneousTheme
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    val apiService by inject<ApiService>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiscellaneousTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }


        lifecycleScope.launch {
            fetchName()
        }
    }

    private suspend fun fetchName() = withContext(Dispatchers.IO) {
        try {
            val data = apiService.fetchSampleData(
                NameRqDTO(
                    name = "Toru"
                )
            )
            Log.d("MainActivity", "Fetched name: ${data.name}, Age: ${data.age}")
        } catch (exception: Exception) {
            Log.e("MainActivity", "Error fetching name: ${exception.message}")
            return@withContext
        }

    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MiscellaneousTheme {
        Greeting("Android")
    }
}