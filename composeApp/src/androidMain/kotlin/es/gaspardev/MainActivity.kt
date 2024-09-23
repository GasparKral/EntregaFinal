package es.gaspardev

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import es.gaspardev.Ui.components.AddNewTaskButton

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App()
            AddNewTaskButton(onClick = {}, Modifier.fillMaxSize())
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}