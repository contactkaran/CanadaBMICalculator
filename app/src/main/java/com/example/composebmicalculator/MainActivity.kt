package com.example.composebmicalculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composebmicalculator.models.AppViewModel
import com.example.composebmicalculator.ui.theme.ComposeBMICalculatorTheme
import com.example.composebmicalculator.ui.theme.background
import com.example.composebmicalculator.ui.theme.green

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBMICalculatorTheme {
                val viewModel = AppViewModel()
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    MainScreen(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = background),
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}

@Composable
fun BaseNote() {
    Text(
        text = "Note: For persons 65 years and older the 'normal' range may begin slightly above BMI 18.5 and extend into the 'overweight' range.",
        fontStyle = FontStyle.Italic,
        fontSize = 8.sp,
        textAlign = TextAlign.Center,
        color = Color.White
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier, viewModel: AppViewModel
) {
    val state = viewModel.state
    var height = state.height
    var weight = state.weight
    var calculatedBMI = state.calculatedBMI
    var context = LocalContext.current
    var classification = state.classification
//    val decimalFormat = DecimalFormat("*.*")
//    decimalFormat.roundingMode = RoundingMode.CEILING
    val cardColor = classification.color

    Column(
        modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Yellow)
                .padding(top = 10.dp)
                .align(Alignment.CenterHorizontally),
            shape = RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp)
        ) {
            Text(
                text = "Canadian BMI calculator",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                style = TextStyle(
                    color = green,
                    fontStyle = FontStyle.Normal,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
        Divider(
            modifier = Modifier.fillMaxWidth(), color = Color.LightGray, thickness = 1.dp
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            TextField(
                value = height,
                onValueChange = {
                    if (state.height.isBlank() && it == ".") {
                        Toast.makeText(
                            context, "Please enter your Height in centimeters", Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        viewModel.changeHeight(height)
                    }
                },
                textStyle = TextStyle(
                    fontSize = 16.sp, color = Color.White, fontWeight = FontWeight.Bold
                ),
                label = {
                    Text(
                        "Your Height", color = Color.White
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = green,
                    unfocusedBorderColor = Color.Green,
                    disabledBorderColor = Color.Blue,
                    containerColor = background
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "cm",
                fontSize = 14.sp,
                color = Color.White,
                fontWeight = FontWeight.Normal
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            TextField(
                value = weight,
                onValueChange = {
                    if (state.weight.isBlank() && it == ".") {
                        Toast.makeText(
                            context, "Please enter your weight in Kilograms", Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        viewModel.changeWeight(it)
                    }
                },
                textStyle = TextStyle(
                    fontSize = 16.sp, color = Color.White, fontWeight = FontWeight.Bold
                ),
                label = {
                    Text(
                        "Your Weight", color = Color.White
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = green,
                    unfocusedBorderColor = Color.Green,
                    disabledBorderColor = Color.Blue,
                    containerColor = background
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "kg", fontSize = 14.sp, color = Color.White, fontWeight = FontWeight.Normal
            )
        }
        Spacer(modifier = Modifier.height(10.dp))



        //TODO- Work needed


        Row(modifier = Modifier.fillMaxWidth(1.0f)) {
            Column(modifier = Modifier.fillMaxWidth(0.75f)) {
                Text(
                    text = classification.categoryDesc,
                    color = classification.color,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Medium
                )
                Column(modifier = Modifier.fillMaxWidth(0.25f)) {
                    Card(
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .background(color = cardColor),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                        shape = RoundedCornerShape(15.dp)
                    ) {}
                }
            }
        }
        Spacer(modifier = Modifier.fillMaxWidth())
        Box(contentAlignment = Alignment.Center) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                Text(
                    text = classification.toString(),
                    color = classification.color,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.padding(top = 55.dp, bottom = 55.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                ) {
                    Text(
                        text = calculatedBMI.toString(),
                        color = classification.color,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
            Divider(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                color = Color.LightGray,
                thickness = 1.dp
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            ) {
                BMIChart()
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 5.dp)
            ) {
                BaseNote()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen(modifier = Modifier, viewModel = AppViewModel())
}


@Composable
fun BMIChart() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        Row {
            Text(
                text = "Classification",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(1f),
                color = Color.White
            )
            Text(
                text = "BMI (kg/m2)",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(1f),
                color = Color.White
            )
            Text(
                text = "Risk of health problems",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(1f),
                color = Color.White
            )
        }
        Divider(modifier = Modifier.height(3.dp))
        Spacer(modifier = Modifier.height(3.dp))
        TableRow(
            classification = "Underweight", bmiCategory = "< 18.5", risk = "Increased"
        )
        Spacer(modifier = Modifier.height(5.dp))
        TableRow(
            classification = "Normal Weight", bmiCategory = "18.5 to 24.9", risk = "Least"
        )
        Spacer(modifier = Modifier.height(5.dp))
        TableRow(
            classification = "Overweight", bmiCategory = "25 to 29.9", risk = "Increased"
        )
        Spacer(modifier = Modifier.height(5.dp))
        TableRow(
            classification = "Obese Class I", bmiCategory = "30 to 34.9", risk = "High"
        )
        Spacer(modifier = Modifier.height(5.dp))
        TableRow(
            classification = "Obese Class II", bmiCategory = "35.0 to 39.9", risk = "Very High"
        )
        Spacer(modifier = Modifier.height(5.dp))
        TableRow(
            classification = "Obese Class III",
            bmiCategory = "40.0 or more",
            risk = "Extremely High"
        )
    }
}

@Composable
fun TableRow(classification: String, bmiCategory: String, risk: String) {
    Row {
        Text(
            text = classification,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.weight(1f),
            color = Color.White
        )
        Text(
            text = bmiCategory,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.weight(1f),
            color = Color.White
        )
        Text(
            text = risk,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.weight(1f),
            color = Color.White
        )
    }
}
