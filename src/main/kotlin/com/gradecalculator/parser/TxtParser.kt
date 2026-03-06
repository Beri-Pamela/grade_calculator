package com.gradecalculator.parser

import com.gradecalculator.model.Student
import java.io.File

object TxtParser {

    fun parse(filePath: String): List<Student> {
        val students = mutableListOf<Student>()

        val lines = File(filePath).readLines()

        // Skip the header line
        for (line in lines.drop(1)) {
            val parts = line.split(",")
            if (parts.size < 3) continue

            val name  = parts[0].trim()
            val id    = parts[1].trim()
            val score = parts[2].trim().toDoubleOrNull() ?: 0.0

            students.add(Student(name, id, score))
        }

        return students
    }
}
