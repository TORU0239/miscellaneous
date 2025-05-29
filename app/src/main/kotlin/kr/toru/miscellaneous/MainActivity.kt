package kr.toru.miscellaneous

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kr.toru.miscellaneous.core.network.model.request.NameRqDTO
import kr.toru.miscellaneous.core.network.model.response.NameRsDTO
import kr.toru.miscellaneous.core.network.service.ApiService
import kr.toru.miscellaneous.ui.theme.MiscellaneousTheme
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val apiService by inject<ApiService>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
    }

    override fun onResume() {
        super.onResume()

        lifecycleScope.launch {
            val name = fetchName() as NameRsDTO
            println("got it. name: ${name.name}, age: ${name.age}")
            Toast.makeText(
                this@MainActivity,
                "Name: ${name.name}, Age: ${name.age}",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private suspend fun fetchName() = withContext(Dispatchers.IO) {
        try {
            val data = apiService.fetchSampleData(
                NameRqDTO(
                    name = "Michael"
                )
            )
            Log.d("MainActivity", "Fetched name: ${data.name}, Age: ${data.age}")
            return@withContext data
        } catch (exception: Exception) {
            Log.e("MainActivity", "Error fetching name: ${exception.message}")
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