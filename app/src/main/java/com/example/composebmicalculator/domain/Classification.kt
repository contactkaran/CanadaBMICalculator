package com.example.composebmicalculator.domain

import androidx.compose.ui.graphics.Color
import com.example.composebmicalculator.ui.theme.blue
import com.example.composebmicalculator.ui.theme.green
import com.example.composebmicalculator.ui.theme.orange
import com.example.composebmicalculator.ui.theme.red


/* SOURCE: https://www.canada.ca/en/health-canada/services/food-nutrition/healthy-eating/healthy-weights/canadian-guidelines-body-weight-classification-adults/body-mass-index-nomogram.html

BMI = weight(kg)/height(m)2

There are four categories of BMI ranges in the Canadian weight classification system. These are:

CLASSIFICATION  BMI Category(kg/m2) RISK OF DEVELOPING HEALTH PROBLEMS
underweight (BMI less than 18.5) -      INCREASED
normal weight (BMIs 18.5 to 24.9) -     LEAST
overweight (BMIs 25 to 29.9) -          INCREASED
obese (BMI 30 and over) -               HIGH

Note: For persons 65 years and older the 'normal' range may begin slightly above BMI 18.5 and extend into the 'overweight' range.
*/



open class Classification(val categoryDesc: String, val color: Color) {

    object BLANK: Classification("", Color.White)

    object Underweight : Classification("Underweight", blue)

    object NormalWeight : Classification("Normal Weight", green)

    object Overweight : Classification("Overweight", orange)

    object ObeseClass1 : Classification("Obese Class I", red)

    object ObeseClass2 : Classification("Obese Class II", red)

    object ObeseClass3 : Classification("Obese Class III", red)

    companion object
    fun classificationBMI(scoreBMI: Float): Classification {
        return when(scoreBMI){
            in Double.MIN_VALUE..18.5 ->  Underweight
            in Double.MIN_VALUE..24.9 -> NormalWeight
            in Double.MIN_VALUE..29.9 -> Overweight
            in Double.MIN_VALUE..34.9 -> ObeseClass1
            in Double.MIN_VALUE..39.9 -> ObeseClass2
            else ->  ObeseClass3

        }
    }


}