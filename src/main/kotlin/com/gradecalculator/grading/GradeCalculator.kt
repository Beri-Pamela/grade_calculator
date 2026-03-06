package com.gradecalculator.grading

import com.gradecalculator.model.Student

object GradeCalculator {

    fun assignGrade(score: Double): String {
        return when {
            score >= 80 -> "A"
            score >= 70 -> "B+"
            score >= 60 -> "B"
            score >= 55 -> "C+"
            score >= 50 -> "C"
            score >= 45 -> "D+"
            score >= 40 -> "D"
            else        -> "F"
        }
    }

    fun processStudents(students: List<Student>): List<Student> {
        return students.map { it.copy(grade = assignGrade(it.score)) }
    }
}