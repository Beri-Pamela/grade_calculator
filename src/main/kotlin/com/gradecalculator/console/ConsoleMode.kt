package com.gradecalculator.console

import com.gradecalculator.exporter.ExcelExporter
import com.gradecalculator.grading.GradeCalculator
import com.gradecalculator.parser.CsvParser
import com.gradecalculator.parser.ExcelParser
import com.gradecalculator.parser.TxtParser

object ConsoleMode {

    fun start() {
        println("===========================================")
        println("       GRADE CALCULATOR - Console Mode     ")
        println("===========================================")

        // Ask for input file
        print("Enter the full path of your input file: ")
        val filePath = readLine()?.trim() ?: return

        // Detect file type and parse
        val students = when {
            filePath.endsWith(".csv")  -> CsvParser.parse(filePath)
            filePath.endsWith(".xlsx") -> ExcelParser.parse(filePath)
            filePath.endsWith(".txt")  -> TxtParser.parse(filePath)
            else -> {
                println("Unsupported file type! Please use .csv, .xlsx or .txt")
                return
            }
        }

        if (students.isEmpty()) {
            println("No students found in the file!")
            return
        }

        // Calculate grades
        val gradedStudents = GradeCalculator.processStudents(students)

        // Display results in console
        println("\n===========================================")
        println("                 RESULTS                   ")
        println("===========================================")
        println("%-20s %-10s %-10s %-5s".format("Name", "ID", "Score", "Grade"))
        println("-".repeat(50))
        for (student in gradedStudents) {
            println("%-20s %-10s %-10.1f %-5s".format(
                student.name, student.id, student.score, student.grade))
        }
        println("-".repeat(50))

        // Ask for output file path
        print("\nEnter the output file path (e.g. C:\\results.xlsx): ")
        val outputPath = readLine()?.trim() ?: return

        // Export to Excel
        ExcelExporter.export(gradedStudents, outputPath)
        println("Done!")
    }
}