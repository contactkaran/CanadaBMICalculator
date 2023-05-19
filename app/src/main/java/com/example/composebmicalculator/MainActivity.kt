package com.example.composebmicalculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composebmicalculator.models.AppViewModel
import com.example.composebmicalculator.ui.theme.ComposeBMICalculatorTheme
import com.example.composebmicalculator.ui.theme.green
import java.math.RoundingMode
import java.text.DecimalFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBMICalculatorTheme {
                val viewModel = AppViewModel()

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = Color.DarkGray),
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: AppViewModel
) {
    val state = viewModel.state
    var weight = state.weight
    var height = state.height
    var calculatedBMI = state.calculatedBMI
    var context = LocalContext.current
    var classification = state.classification
    val decimalFormat = DecimalFormat("*.*")
    decimalFormat.roundingMode = RoundingMode.CEILING

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .padding(10.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "BMI calculator",
                style = TextStyle(
                    color = green,
                    fontStyle = FontStyle.Normal,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {

                TextField(
                    value = height,
                    onValueChange = {

                        if (state.height.isBlank() && it == ".") {
                            Toast.makeText(context, "Enter your Height", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            viewModel.changeHeight(height)
                        }
                    },
                    label = { "Your height" },
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = green,
                        unfocusedIndicatorColor = Color.White,
                        containerColor = Color.Blue
                    )
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "cm",
                    fontSize = 14.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Normal
                )

            }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen(modifier = Modifier, viewModel = AppViewModel())
}