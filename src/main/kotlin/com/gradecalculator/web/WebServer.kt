package com.gradecalculator.web

import com.gradecalculator.exporter.ExcelExporter
import com.gradecalculator.grading.GradeCalculator
import com.gradecalculator.parser.CsvParser
import com.gradecalculator.parser.ExcelParser
import com.gradecalculator.parser.TxtParser
import io.javalin.Javalin
import io.javalin.http.staticfiles.Location

object WebServer {

    fun start() {
        val app = Javalin.create { config ->
            config.staticFiles.add("/static", Location.CLASSPATH)
        }.start(7070)

        println("===========================================")
        println("  GRADE CALCULATOR - Web GUI Mode")
        println("  Open your browser and go to:")
        println("  http://localhost:7070")
        println("===========================================")

        // API endpoint to calculate grades
        app.post("/calculate") { ctx ->
            val filePath   = ctx.formParam("filePath") ?: ""
            val outputPath = ctx.formParam("outputPath") ?: ""

            try {
                // Parse the input file
                val students = when {
                    filePath.endsWith(".csv")  -> CsvParser.parse(filePath)
                    filePath.endsWith(".xlsx") -> ExcelParser.parse(filePath)
                    filePath.endsWith(".txt")  -> TxtParser.parse(filePath)
                    else -> {
                        ctx.result("Unsupported file type!")
                        return@post
                    }
                }

                if (students.isEmpty()) {
                    ctx.result("No students found in the file!")
                    return@post
                }

                // Calculate grades
                val gradedStudents = GradeCalculator.processStudents(students)

                // Export to Excel
                ExcelExporter.export(gradedStudents, outputPath)

                // Return results as JSON
                ctx.json(gradedStudents)

            } catch (e: Exception) {
                ctx.status(500).result("Error: ${e.message}")
            }
        }
    }
}