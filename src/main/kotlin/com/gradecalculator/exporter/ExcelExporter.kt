package com.gradecalculator.exporter

import com.gradecalculator.model.Student
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File

object ExcelExporter {

    fun export(students: List<Student>, outputPath: String) {
        val workbook = XSSFWorkbook()
        val sheet = workbook.createSheet("Grades")

        // Create header row
        val headerRow = sheet.createRow(0)
        headerRow.createCell(0).setCellValue("Name")
        headerRow.createCell(1).setCellValue("ID")
        headerRow.createCell(2).setCellValue("Score")
        headerRow.createCell(3).setCellValue("Grade")

        // Create a row for each student
        students.forEachIndexed { index, student ->
            val row = sheet.createRow(index + 1)
            row.createCell(0).setCellValue(student.name)
            row.createCell(1).setCellValue(student.id)
            row.createCell(2).setCellValue(student.score)
            row.createCell(3).setCellValue(student.grade)
        }

        // Auto-size all columns
        for (i in 0..3) {
            sheet.autoSizeColumn(i)
        }

        // Write to file
        val outputFile = File(outputPath)
        outputFile.outputStream().use { workbook.write(it) }
        workbook.close()

        println("Results exported to: $outputPath")
    }
}