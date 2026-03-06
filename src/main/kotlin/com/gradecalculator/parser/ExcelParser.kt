package com.gradecalculator.parser

import com.gradecalculator.model.Student
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File

object ExcelParser {

    fun parse(filePath: String): List<Student> {
        val students = mutableListOf<Student>()

        val workbook = XSSFWorkbook(File(filePath).inputStream())
        val sheet = workbook.getSheetAt(0)

        // Skip the header row (row 0)
        val rows = sheet.drop(1)

        for (row in rows) {
            val name  = row.getCell(0)?.toString() ?: continue
            val id    = row.getCell(1)?.toString() ?: continue
            val score = row.getCell(2)?.numericCellValue ?: 0.0
            students.add(Student(name, id, score))
        }

        workbook.close()
        return students
    }
}