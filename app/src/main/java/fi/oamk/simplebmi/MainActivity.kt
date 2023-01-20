package fi.oamk.simplebmi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fi.oamk.simplebmi.ui.theme.SimpleBMITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleBMITheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SimpleBMI()
                }
            }
        }
    }

    @Composable
    fun SimpleBMI() {
        var heightInput: String by remember { mutableStateOf("") }
        var weightInput by remember { mutableStateOf("") }
        val weight = weightInput.toFloatOrNull() ?: 0.0f
        val height = heightInput.toFloatOrNull() ?: 0.0f
        val bmi = if (weight > 0 && height > 0) weight / (height*height) else 0.0f
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = stringResource(R.string.bmi),
                fontSize=24.sp,
                color=MaterialTheme.colors.primary,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 16.dp)
                )
            OutlinedTextField(
                value = heightInput,
                onValueChange = {heightInput = it.replace(',','.')},
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                label = {Text(stringResource(R.string.height))},
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = weightInput,
                onValueChange = {weightInput = it.replace(',','.')},
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                label = {Text(stringResource(R.string.weight))},
                modifier = Modifier.fillMaxWidth()
            )
            Text(text=stringResource(R.string.bmi_result, String.format("%.2f",bmi)))
        }
    }
}
