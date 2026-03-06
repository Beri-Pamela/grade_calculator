package com.gradecalculator.model

data class Student(
    val name: String,
    val id: String,
    val score: Double,
    var grade: String = ""
)