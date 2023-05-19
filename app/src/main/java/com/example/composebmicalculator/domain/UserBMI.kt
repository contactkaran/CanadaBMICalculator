package com.example.composebmicalculator.domain

data class UserBMI(
    val classification: Classification = Classification.BLANK,
    val weight: String = ".",  //TODO- change to String if Errors in testing
    val height: String = ".",
    val calculatedBMI:Float = 0.0f,
    val lowerWeightBound:Float = 0f,
    val higherWeightBound:Float = 0f
)
