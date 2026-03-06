package com.gradecalculator.parser

import com.gradecalculator.model.Student
import org.apache.commons.csv.CSVFormat
import java.io.File

object CsvParser {

    fun parse(filePath: String): List<Student> {
        val students = mutableListOf<Student>()

        val reader = File(filePath).bufferedReader()
        val csvFormat = CSVFormat.DEFAULT.builder()
            .setHeader()
            .setSkipHeaderRecord(true)
            .build()

        val records = csvFormat.parse(reader)

        for (record in records) {
            val name  = record.get("name")
            val id    = record.get("id")
            val score = record.get("score").toDoubleOrNull() ?: 0.0
            students.add(Student(name, id, score))
        }

        return students
    }
}