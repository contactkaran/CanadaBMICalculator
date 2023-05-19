package com.example.composebmicalculator.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.composebmicalculator.domain.Classification
import com.example.composebmicalculator.domain.Classification.BLANK.classificationBMI
import com.example.composebmicalculator.domain.UserBMI

class AppViewModel: ViewModel() {

    var state by mutableStateOf(UserBMI())

    private fun bmiCalculation(){
        state = if(state.height.isBlank() && state.weight.isBlank()){
            val heightInMeters = state.height.toFloat() / 100
            val calculatingBMI = state.weight.toFloat() / (heightInMeters * heightInMeters)
            val findingBMIClass = classificationBMI(calculatingBMI)
            state.copy(
                calculatedBMI = calculatingBMI,
                classification = findingBMIClass
            )
        } else {
            state.copy(
                calculatedBMI = 0f,
                classification = Classification.BLANK
            )
        }
    }

    fun changeHeight(height: String) {
        TODO("Not yet implemented")
        state = state.copy(
            height = height
        )
        bmiCalculation()
    }

}